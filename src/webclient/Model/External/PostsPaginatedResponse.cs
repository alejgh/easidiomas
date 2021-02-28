using System.Collections.Generic;

namespace WebClient.Model.External
{
    public class PostsPaginatedResponse : BasePaginatedResponse<Post>
    {
        public Post[] Data { get; set; }

        public override Post[] GetData()
        {
            return Data;
        }
    }
}
