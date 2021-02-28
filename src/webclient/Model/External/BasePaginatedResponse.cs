using System;
namespace WebClient.Model.External
{
    public abstract class BasePaginatedResponse<T>
    {
        public class PaginationLinks
        {
            public string Self { get; set; }
            public string First { get; set; }
            public string Prev { get; set; }
            public string Next { get; set; }
            public string Last { get; set; }
        }

        // this structure was stablished by design for all services that offer pagination
        public PaginationLinks Links { get; set; }

        public int Count { get; set; }
        public int Total { get; set; }

        public abstract T[] GetData();
    }
}
