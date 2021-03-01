using Google.Cloud.Translation.V2;
using languageidentificationservice.Kafka;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace languageidentificationservice.Identification
{
    public class LanguageIdentificator
    {
        private TranslationClient _translationClient;
        private readonly KafkaProducer<long, string> _producer;

        public LanguageIdentificator(KafkaProducer<long, string> producer)
        {
            _translationClient = TranslationClient.Create();
            _producer = producer;
        }

        public Detection Identificate(long postId, string textToIdentificate)
        {
            var detection = _translationClient.DetectLanguage(text: textToIdentificate);
            _producer.Produce("lang_detection", new Confluent.Kafka.Message<long, string>
            {
                Key = postId,
                Value = detection.Language
            });
            return detection;
        }
    }
}
