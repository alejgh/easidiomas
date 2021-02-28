using System;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using PostsService.Kafka.Deserializers;
using PostsService.Model;
using PostsService.Service;

namespace PostsService.Kafka.Consumers
{

    /// <summary>
    /// Class that listens to messages received from the offensive text detection service.
    /// </summary>
    public class OffensiveTextDetectionConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, bool> consumer;
        private readonly ILogger<OffensiveTextDetectionConsumer> _logger;
        private readonly IServiceScopeFactory _scopeFactory;

        public OffensiveTextDetectionConsumer(IConfiguration configuration, ILogger<OffensiveTextDetectionConsumer> logger,
            IServiceScopeFactory scopeFactory)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["OFFENSIVE_TEXT_DETECTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];

            this.consumer = new KafkaConsumer<long, bool>(endpoint, topic, groupId,
                onOffensiveTextDetected, new LongDeserializer(), new BooleanDeserializer());
            _logger = logger;
            _scopeFactory = scopeFactory;
        }

        /// <summary>
        /// Callback to be called when a new message from the offensive text detection service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="isOffensive">Whether the post is considered to be offensive or not</param>
        private async void onOffensiveTextDetected(long postId, bool isOffensive)
        {
            _logger.LogInformation($"On offensive text detected was called: id='{postId}', isOffensive='{isOffensive}'");

            using (var scope = _scopeFactory.CreateScope())
            {
                _logger.LogDebug($"Asking for an instance of posts service...");
                IPostsService postsService = scope.ServiceProvider.GetRequiredService<IPostsService>();

                Post post = await postsService.GetPost(postId);
                if (post == null)
                {
                    _logger.LogDebug($"Post with id '{postId}' was not found. Nothing to update...");
                    return;
                }

                post.IsOffensive = isOffensive;
                _logger.LogDebug($"Calling service to update post: {post}");
                await postsService.UpdatePost(post);
            }
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
