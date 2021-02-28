using System;
using System.Collections.Generic;

namespace WebClient.Model.External
{
    public class Statistics
    {
        public class RegisteredUsersData
        {
            public int Total { get; set; }
            public IDictionary<string, int> learning { get; set; }
            public IDictionary<string, int> nnative { get; set; }
        }

        public RegisteredUsersData RegisteredUsers { get; set; }
        public IDictionary<string, int> CreatedPosts { get; set; }
        public int CreatedChats { get; set; }
    }
}
