using Confluent.Kafka;
using NLog;
using NLog.Config;
using NLog.Layouts;
using NLog.Targets;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace languageidentificationservice.Kafka
{
    /// <summary>
    /// Custom NLog target that sends logs to a Kafka queue.
    ///
    /// This class can be added as a target for NLog, so logs sents
    /// with a logger will call the WriteAsyncTask method.
    /// This method just sends the log content (message) through the kafka
    /// queue with a topic and a key. Both the topic and the key are specified
    /// in the nlog.config file.
    /// </summary>
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
