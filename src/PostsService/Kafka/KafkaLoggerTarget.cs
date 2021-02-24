using System;
using System.Collections.Concurrent;
using System.Threading;
using System.Threading.Tasks;
using Confluent.Kafka;
using NLog;
using NLog.Common;
using NLog.Config;
using NLog.Layouts;
using NLog.Targets;
using PostsService.Kafka;

namespace PostsService.Kafka
{
    public class KafkaLoggerTarget : AsyncTaskTarget
    {
        private readonly KafkaProducer<string, string> producer;

        [RequiredParameter]
        public Layout Topic { get; set; }

        [RequiredParameter]
        public Layout Key { get; set; }

        public KafkaLoggerTarget(KafkaClientHandle handle)
        {
            producer = new KafkaProducer<string, string>(handle);
        }

        protected override async Task WriteAsyncTask(LogEventInfo logEvent, CancellationToken cancellationToken)
        {
            // Using RenderLogEvent will allow NLog-Target to make optimal reuse of StringBuilder-buffers.  
            string topic = base.RenderLogEvent(this.Topic, logEvent);
            string key = base.RenderLogEvent(this.Key, logEvent);
            string msg = base.RenderLogEvent(this.Layout, logEvent);

            await producer.ProduceAsync(topic, new Message<string, string>()
            {
                Key = key,
                Value = msg
            });
        }
    }
}
