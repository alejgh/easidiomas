using System;

namespace WebClient.Model.External
{
    public class Post
    {
        public long Id { get; set; }

        public long AuthorId { get; set; }

        public DateTime CreatedDate { get; set; }

        public string Content { get; set; }

        public bool IsOffensive { get; set; }

        public string Language { get; set; }

        public int Likes { get; set; }

        public string[] Topics { get; set; }
    }
}
