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
using StatisticsService;

namespace PostsService.Kafka.Consumers
{
    /// <summary>
    /// Class that listens to messages received from the language detection service.
    /// </summary>
    public class LanguageDetectionConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, string> _consumer;
        private readonly ILogger<LanguageDetectionConsumer> _logger;
        private readonly IServiceScopeFactory _scopeFactory;

        private readonly string _statisticsServiceAddress;

        public LanguageDetectionConsumer(IConfiguration configuration, ILogger<LanguageDetectionConsumer> logger,
            IServiceScopeFactory scopeFactory)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["LANGUAGE_DETECTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];
            _statisticsServiceAddress = configuration["STATISTICS_SERVICE_ENDPOINT"];

            _consumer = new KafkaConsumer<long, string>(endpoint, topic, groupId,
                onLanguageIdentification, new LongDeserializer());
            _logger = logger;
            _scopeFactory = scopeFactory;
        }

        /// <summary>
        /// Callback to be called when a new message from the language detection service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="language">Detected language of the post</param>
        private async void onLanguageIdentification(long postId, string language)
        {
            _logger.LogDebug($"OnLanguageIdentification was called with id={postId}, lang={language}");

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

                post.Language = language;
                _logger.LogDebug($"Calling service to update language of post to: {post.Language}");
                await postsService.UpdatePost(post);
            }

            // call the statistics service with information about this post
            //StatisticsServiceClient.EndpointConfiguration config = new StatisticsServiceClient.EndpointConfiguration();
            //StatisticsServiceClient client = new StatisticsServiceClient(config, _statisticsServiceAddress);
            //await client.registerPostCreatedEventAsync(postId.ToString(), language);
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => _consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
