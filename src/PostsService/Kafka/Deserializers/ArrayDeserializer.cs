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
    public class ArrayDeserializer : IDeserializer<string[]>
    {

        public string[] Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            if (isNull) return null;

            byte[] bytes = data.ToArray();
            IList<string> res = new List<string>();
            int i = 1;
            while (i < bytes.Length)
            {
                int numBytesString = bytes[i];
                i += 1;
                string element = System.Text.Encoding.UTF8.GetString(bytes.Skip(i).Take(numBytesString).ToArray());
                res.Add(element);
                i += numBytesString;
            }

            return res.ToArray();
        }
    }
}
