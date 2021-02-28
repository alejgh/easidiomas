using System;
using System.Collections.Generic;

namespace WebClient.Model.External
{
    public class Statistics
    {
        public class StatisticsDict
        {
            public class StatisticsEntry
            {
                public string Key { get; set; }
                public string Value { get; set; }
            }

            public StatisticsEntry[] Entry { get; set; }
        }

        public class RegisteredUsersData
        {
            public int Total { get; set; }
            public StatisticsDict learning { get; set; }
            public StatisticsDict nnative { get; set; }
        }

        public RegisteredUsersData RegisteredUsers { get; set; }
        public StatisticsDict CreatedPosts { get; set; }
        public int CreatedChats { get; set; }
    }
}
