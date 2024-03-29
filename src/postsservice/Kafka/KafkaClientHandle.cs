﻿using System;
using Confluent.Kafka;
using Microsoft.Extensions.Configuration;

namespace PostsService.Kafka
{
    /// <summary>
    /// Basic Client Handle to centralize configuration sent to a Kafka Producer.
    /// </summary>
    public class KafkaClientHandle
    {
        IProducer<byte[], byte[]> kafkaProducer;

        public KafkaClientHandle(IConfiguration config)
        {
            var conf = new ProducerConfig();
            conf.BootstrapServers = config["KAFKA_ENDPOINT"];
            conf.RequestTimeoutMs = 180000; // 180 seconds -> 3 min
            this.kafkaProducer = new ProducerBuilder<byte[], byte[]>(conf).Build();
        }

        public Handle Handle { get => this.kafkaProducer.Handle; }

        public void Dispose()
        {
            // Block until all outstanding produce requests have completed (with or
            // without error).
            kafkaProducer.Flush();
            kafkaProducer.Dispose();
        }
    }
}
