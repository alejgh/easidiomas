using System;
using Confluent.Kafka;

namespace PostsService.Kafka.Deserializers
{
    /// <summary>
    /// Custom deserializer to parse booleans from Kafka.
    ///
    /// Adapted to the response obtained by the offensive text detection service.
    /// </summary>
    public class BooleanDeserializer : IDeserializer<bool>
    {
        public bool Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            if (isNull) throw new ArgumentNullException($"Null data encountered deserializing boolean value.");

            byte[] array = data.ToArray();
            return array.Length != 0;
        }
    }
}
