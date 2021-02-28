using System;
using System.Threading;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using PostsService.Kafka.Deserializers;

namespace PostsService.Kafka.Consumers
{

    /// <summary>
    /// Class that listens to messages received from the offensive text detection service.
    /// </summary>
    public class OffensiveTextDetectionConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, bool> consumer;
        private readonly ILogger<OffensiveTextDetectionConsumer> logger;

        public OffensiveTextDetectionConsumer(IConfiguration configuration, ILogger<OffensiveTextDetectionConsumer> logger)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["OFFENSIVE_TEXT_DETECTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];

            this.consumer = new KafkaConsumer<long, bool>(endpoint, topic, groupId,
                onOffensiveTextDetected, new LongDeserializer(), new BooleanDeserializer());
            this.logger = logger;
        }

        /// <summary>
        /// Callback to be called when a new message from the offensive text detection service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="isOffensive">Whether the post is considered to be offensive or not</param>
        private void onOffensiveTextDetected(long postId, bool isOffensive)
        {
            logger.LogDebug("OnOffensiveText detected was called");
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
