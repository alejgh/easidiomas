using System;
using System.Threading;
using Confluent.Kafka;

namespace PostsService.Kafka
{
    /// <summary>
    /// Basic Kafka Consumer implementation that listens to a topic.
    ///
    /// Based on the one available at https://github.com/confluentinc/confluent-kafka-dotnet/blob/master/examples/Web/RequestTimeConsumer.cs
    /// </summary>
    public class KafkaConsumer<K, V>
    {
        public string Topic { get; set; }
        public Action<K, V> Callback { get; set; }

        private readonly IConsumer<K, V> consumer;

        /// <summary>
        /// Base Constructor for the Kafka Consumer
        /// </summary>
        /// <param name="endpoint">Endpoint of Kafka (e.g. localhost:9092)</param>
        /// <param name="topic">Topic that the consumer will listen to.</param>
        /// <param name="groupID">Group id of the consumer in Kafka (see https://docs.confluent.io/platform/current/clients/consumer.html#consumer-groups)</param>
        /// <param name="callback">Action to be called when a new message is consumed.</param>
        /// <param name="keyDeserializer">Optional: Custom deserializer to parse keys received from the topic.</param>
        /// <param name="valueDeserializer">Optional: Custom deserializer to parse values received from the topic.</param>
        public KafkaConsumer(string endpoint, string topic, string groupID,
            Action<K, V> callback, IDeserializer<K> keyDeserializer = null,
            IDeserializer<V> valueDeserializer = null)
        {
            Topic = topic;
            Callback = callback;

            var consumerConfig = new ConsumerConfig();
            consumerConfig.BootstrapServers = endpoint;
            consumerConfig.AutoOffsetReset = AutoOffsetReset.Latest;
            consumerConfig.GroupId = groupID;

            var consumerBuilder = new ConsumerBuilder<K, V>(consumerConfig);
            if (keyDeserializer != null) consumerBuilder.SetKeyDeserializer(keyDeserializer);
            if (valueDeserializer != null) consumerBuilder.SetValueDeserializer(valueDeserializer);

            this.consumer = consumerBuilder.Build();
        }

        /// <summary>
        /// Starts the main consumer loop.
        ///
        /// This loop should be run in a separate thread.
        /// </summary>
        /// <param name="stoppingToken"></param>
        public void StartConsumerLoop(CancellationToken stoppingToken)
        {
            this.consumer.Subscribe(Topic);
            while (!stoppingToken.IsCancellationRequested)
            {
                try
                {
                    var cr = this.consumer.Consume(stoppingToken);
                    Callback(cr.Message.Key, cr.Message.Value);
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
