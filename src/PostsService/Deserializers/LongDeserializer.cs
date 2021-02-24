using System;
using Confluent.Kafka;

namespace PostsService.Deserializers
{
    public class LongDeserializer : IDeserializer<long>
    {
        public long Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            return Convert.ToInt64(data.ToArray());
        }
    }
}
