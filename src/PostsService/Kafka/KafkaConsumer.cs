using System;
using System.Threading;
using Confluent.Kafka;

namespace PostsService.Kafka
{
    /// <summary>
    /// Basic Kafka Consumer implementation that listens to multiple topics
    ///
    /// Based on the one available at https://github.com/confluentinc/confluent-kafka-dotnet/blob/master/examples/Web/RequestTimeConsumer.cs
    /// </summary>
    public class KafkaConsumer<K, V>
    {
        public string Topic { get; set; }
        public Action<K, V> Callback { get; set; }

        private readonly IConsumer<K, V> consumer;

        public KafkaConsumer(string endpoint, string topic, string groupID,
            Action<K, V> callback, IDeserializer<K> keyDeserializer = null,
            IDeserializer<V> valueDeserializer = null)
        {
            Topic = topic;
            Callback = callback;

            var consumerConfig = new ConsumerConfig();
            consumerConfig.BootstrapServers = endpoint;
            consumerConfig.GroupId = groupID;

            var consumerBuilder = new ConsumerBuilder<K, V>(consumerConfig);
            if (keyDeserializer != null) consumerBuilder.SetKeyDeserializer(keyDeserializer);
            if (valueDeserializer != null) consumerBuilder.SetValueDeserializer(valueDeserializer);

            this.consumer = consumerBuilder.Build();
        }

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
