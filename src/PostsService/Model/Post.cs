using System;
namespace PostsService.Model
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

        public override string ToString()
        {
            return $"Post [ID: {Id} - Author: {AuthorId} - Content: {Content}]";
        }
    }
}
