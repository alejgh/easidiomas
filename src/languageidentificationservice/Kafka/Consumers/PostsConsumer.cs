using languageidentificationservice.Identification;
using languageidentificationservice.Kafka.Deserializers;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using StatisticsService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace languageidentificationservice.Kafka.Consumers
{
    public class PostsConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, string> _consumer;
        private readonly ILogger<PostsConsumer> _logger;
        private readonly IServiceScopeFactory _scopeFactory;
        private string _statisticsServiceAddress;

        public PostsConsumer(IConfiguration configuration, ILogger<PostsConsumer> logger,
            IServiceScopeFactory scopeFactory)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["POSTS_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];
            _statisticsServiceAddress = configuration["STATISTICS_SERVICE_ENDPOINT"];


            _consumer = new KafkaConsumer<long, string>(endpoint, topic, groupId,
                OnPost, new LongDeserializer());
            _logger = logger;
            _logger.LogInformation($"Creating a post consumer for endpoint [{endpoint}], topic [{topic}], groupId [{groupId}] and _statisticsServiceAddress [{_statisticsServiceAddress}].");
            _scopeFactory = scopeFactory;
        }

        /// <summary>
        /// Callback to be called when a new message from the language detection service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="language">Detected language of the post</param>
        private async void OnPost(long postId, string postText)
        {
            _logger.LogDebug($"OnPost was called with id={postId}, text={postText}");

            using (var scope = _scopeFactory.CreateScope())
            {
                _logger.LogDebug($"Asking for the language identification...");
                LanguageIdentificator languageIdentificator = scope.ServiceProvider.GetRequiredService<LanguageIdentificator>();
                var detection = languageIdentificator.Identificate(postId, postText);

                _logger.LogDebug($"Detection [{detection}] done, posting the result to the kafka queue again.");

                //StatisticsServiceClient.EndpointConfiguration config = new StatisticsServiceClient.EndpointConfiguration();
                //StatisticsServiceClient client = new StatisticsServiceClient(config, _statisticsServiceAddress);
                //await client.registerPostCreatedEventAsync(postId.ToString(), detection.Text);

            }

        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => _consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
