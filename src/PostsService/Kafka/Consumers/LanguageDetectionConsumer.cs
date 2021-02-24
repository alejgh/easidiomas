using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using PostsService.Kafka.Deserializers;

namespace PostsService.Kafka.Consumers
{
    /// <summary>
    /// Class that listens to messages received from the language detection service.
    /// </summary>
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

        /// <summary>
        /// Callback to be called when a new message from the language detection service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="language">Detected language of the post</param>
        private void onLanguageIdentification(long postId, string language)
        {
            logger.LogDebug("OnLanguageIdentification was called");
            // TODO: call the statistics service with information about this post
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
