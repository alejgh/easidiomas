using Confluent.Kafka;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace languageidentificationservice.Kafka.Deserializers
{
    public class LongDeserializer : IDeserializer<long>
    {
        public long Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            var bytes = data.ToArray();
            return BitConverter.ToInt64(bytes.Reverse().ToArray());
        }
    }
}
