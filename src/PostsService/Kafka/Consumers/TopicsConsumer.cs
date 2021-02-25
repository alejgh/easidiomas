using System;
using System.Collections.Generic;
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
    /// Class that listens to messages received from the topic extraction service.
    /// </summary>
    public class TopicsConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, string[]> _consumer;
        private readonly ILogger<TopicsConsumer> _logger;
        private readonly IServiceScopeFactory _scopeFactory;

        public TopicsConsumer(IConfiguration configuration, ILogger<TopicsConsumer> logger,
            IServiceScopeFactory scopeFactory)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["TOPIC_EXTRACTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];

            _consumer = new KafkaConsumer<long, string[]>(endpoint, topic, groupId,
                onTopicsDetected, new LongDeserializer(), new ArrayDeserializer());
            _logger = logger;
            _scopeFactory = scopeFactory;
        }


        /// <summary>
        /// Callback to be called when a new message from the topic extraction service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="topics">List of topics that have been detected</param>
        private async void onTopicsDetected(long postId, string[] topics)
        {
            _logger.LogInformation($"On topics detected was called: id='{postId}', topics='{topics}'");

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

                post.Topics = topics;
                _logger.LogDebug($"Calling service to update post: {post}");
                await postsService.UpdatePost(post);
            }
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => _consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
