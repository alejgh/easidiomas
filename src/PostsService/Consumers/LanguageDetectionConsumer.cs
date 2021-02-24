using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using PostsService.Deserializers;
using PostsService.Kafka;

namespace PostsService.Consumers
{
    public class LanguageDetectionConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, string> consumer;
        private readonly ILogger<LanguageDetectionConsumer> logger;

        public LanguageDetectionConsumer(IConfiguration configuration, ILogger<LanguageDetectionConsumer> logger)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["LANGUAGE_DETECTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];

            this.consumer = new KafkaConsumer<long, string>(endpoint, topic, groupId,
                onLanguageIdentification, new LongDeserializer());
            this.logger = logger;
        }

        private void onLanguageIdentification(long postId, string language)
        {
            logger.LogInformation("OnLanguageIdentification was called");
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
