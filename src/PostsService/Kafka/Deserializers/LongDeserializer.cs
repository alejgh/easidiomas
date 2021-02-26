using System;
using System.Linq;
using Confluent.Kafka;

namespace PostsService.Kafka.Deserializers
{
    /// <summary>
    /// Custom deserializer to parse longs received from kafka.
    ///
    /// The structure of the bytes array should be as follows: an integer
    /// specifying the length of the next string; bytes representing the string;
    /// if there are more strings in the array, another integer specifying
    /// the length of the next string and so on...
    /// </summary>
    public class LongDeserializer : IDeserializer<long>
    {
        public long Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            var bytes = data.ToArray();
            return BitConverter.ToInt64(bytes.Reverse().ToArray());
        }
    }
}
