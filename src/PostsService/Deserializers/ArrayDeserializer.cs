using System;
using System.Collections.Generic;
using System.Linq;
using Confluent.Kafka;

namespace PostsService.Deserializers
{
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
