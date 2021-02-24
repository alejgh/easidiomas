using System;
using System.Collections.Generic;
using System.Linq;
using Confluent.Kafka;

namespace PostsService.Kafka.Deserializers
{
    /// <summary>
    /// Custom deserializer to parse lists of strings received from kafka.
    ///
    /// The structure of the bytes array should be as follows: an integer
    /// specifying the length of the next string; bytes representing the string;
    /// if there are more strings in the array, another integer specifying
    /// the length of the next string and so on...
    /// </summary>
    public class ArrayDeserializer : IDeserializer<IEnumerable<string>>
    {

        public IEnumerable<string> Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            if (isNull) return null;

            byte[] bytes = data.ToArray();
            int numElements = Convert.ToInt16(bytes[0]);
            int i = 1;

            IEnumerable<string> res = new List<string>();
            while (i < bytes.Length)
            {
                int numBytesString = bytes[1];
                string element = Convert.ToBase64String(bytes.Skip(i + 1).Take(numBytesString).ToArray());
                res.Append(element);
                i += numBytesString;
            }

            return res;
        }
    }
}
