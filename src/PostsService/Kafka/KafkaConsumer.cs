using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;
using Confluent.Kafka;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;

namespace PostsService.Kafka
{
    /// <summary>
    /// Basic Kafka Consumer implementation that listens to multiple topics
    ///
    /// Based on the one available at https://github.com/confluentinc/confluent-kafka-dotnet/blob/master/examples/Web/RequestTimeConsumer.cs
    /// </summary>
    public class KafkaConsumer : BackgroundService
    {
        private readonly IEnumerable<string> topics;
        private readonly IConsumer<long, string> consumer;
        private readonly ILogger<KafkaConsumer> logger;

        public KafkaConsumer(IConfiguration config, ILogger<KafkaConsumer> logger)
        {
            var consumerConfig = new ConsumerConfig();
            consumerConfig.BootstrapServers = config["KAFKA_ENDPOINT"];
            consumerConfig.GroupId = config["SERVICE_NAME"];
            this.topics = new[] {
                config["TOPIC_MODELLING_TOPIC"],
                config["OFFENSIVE_TEXT_TOPIC"],
                config["LANGUAGE_DETECTION_TOPIC"]
            };
            this.consumer = new ConsumerBuilder<long, string>(consumerConfig).Build();
            this.logger = logger;
        }

        private void onTopicsDetected(long postId, IEnumerable<string> topics)
        {
            logger.LogDebug("On topics detected was called");
        }

        private void onOffensiveTextDetected(long postId, bool isOffensive)
        {
            logger.LogDebug("OnOffensiveText detected was called");
        }

        private void onLanguageIdentification(long postId, string language)
        {
            logger.LogDebug("OnLanguageIdentification was called");
        }

        protected override Task ExecuteAsync(CancellationToken stoppingToken)
        {
            //new Thread(() => StartConsumerLoop(stoppingToken)).Start();
            return Task.CompletedTask;
        }

        private void StartConsumerLoop(CancellationToken stoppingToken)
        {
            this.consumer.Subscribe(this.topics);
            while (!stoppingToken.IsCancellationRequested)
            {
                try
                {
                    var cr = this.consumer.Consume(stoppingToken);
                    Console.WriteLine(cr.Message.Value);
                }
                catch (OperationCanceledException)
                {
                    break;
                }
                catch (ConsumeException e)
                {
                    // Consumer errors should generally be ignored (or logged) unless fatal.
                    Console.WriteLine($"Consume error: {e.Error.Reason}");

                    if (e.Error.IsFatal)
                    {
                        break;
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine($"Unexpected error: {e}");
                    break;
                }
            }
        }
    }
}
