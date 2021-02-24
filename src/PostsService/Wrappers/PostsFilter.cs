using System;
namespace PostsService.Wrappers
{
    public class PostsFilter
    {
        public long? User { get; set; }
        public string Language { get; set; }

        public PostsFilter()
        {
            User = null;
            Language = null;
        }

        public PostsFilter(long? userID, string language)
        {
            if (userID != null && userID >= 0) User = userID;
            Language = language;
        }
    }
}
