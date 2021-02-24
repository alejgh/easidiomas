﻿using System;
using Confluent.Kafka;

namespace PostsService.Deserializers
{
    public class BooleanDeserializer : IDeserializer<bool>
    {
        public bool Deserialize(ReadOnlySpan<byte> data, bool isNull, SerializationContext context)
        {
            if (isNull) throw new ArgumentNullException($"Null data encountered deserializing boolean value.");

            return Convert.ToBoolean(data.ToArray());
        }
    }
}
