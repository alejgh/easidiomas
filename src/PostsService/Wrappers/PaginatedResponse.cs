using System;
using System.Collections.Generic;

namespace PostsService.Wrappers
{
    public class PaginatedResponse<T>
    {
        public class PaginationLinks
        {
            public string Self { get; set; }
            public string First { get; set; }
            public string Prev { get; set; }
            public string Next { get; set; }
            public string Last { get; set; }

            public PaginationLinks(string baseEndpoint, int count, int total, int offset)
            {
                Self = $"{baseEndpoint}?offset={offset}&limit={count}";
                First = $"{baseEndpoint}?offset=0&limit={count}";
                Last = $"{baseEndpoint}?offset={Math.Floor((double) total / count)}&limit={count}";

                if (offset - count >= 0) Prev = $"{baseEndpoint}?offset={offset - count}&limit={count}";
                if (offset + count < total) Next = $"{baseEndpoint}?offset={offset + count}&limit={count}";
            }
        }

        // this structure was stablished by design for all services that offer pagination
        public PaginationLinks Links { get; set; }

        public int Count { get; set; }
        public int Total { get; set; }
        public IEnumerable<T> Data { get; set; }

        public PaginatedResponse(IEnumerable<T> data, string baseEndpoint,
            int count, int total, int offset)
        {
            Links = new PaginationLinks(baseEndpoint, count, total, offset);
            Data = data;
            Count = count;
            Total = total;
        }
    }
}
