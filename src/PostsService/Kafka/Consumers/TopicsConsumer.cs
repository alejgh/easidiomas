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
    /// Class that listens to messages received from the topic extraction service.
    /// </summary>
    public class TopicsConsumer : BackgroundService
    {
        private readonly KafkaConsumer<long, IEnumerable<string>> consumer;
        private readonly ILogger<TopicsConsumer> logger;

        public TopicsConsumer(IConfiguration configuration, ILogger<TopicsConsumer> logger)
        {
            string endpoint = configuration["KAFKA_ENDPOINT"];
            string topic = configuration["TOPIC_EXTRACTION_TOPIC"];
            string groupId = configuration["SERVICE_NAME"];

            this.consumer = new KafkaConsumer<long, IEnumerable<string>>(endpoint, topic, groupId,
                onTopicsDetected, new LongDeserializer(), new ArrayDeserializer());
            this.logger = logger;
        }


        /// <summary>
        /// Callback to be called when a new message from the topic extraction service is consumed.
        /// </summary>
        /// <param name="postId">ID of the post</param>
        /// <param name="topics">List of topics that have been detected</param>
        private void onTopicsDetected(long postId, IEnumerable<string> topics)
        {
            logger.LogDebug("On topics detected was called");
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            new Thread(() => consumer.StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }
    }
}
