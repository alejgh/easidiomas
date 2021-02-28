using System;
namespace WebClient.Model.External
{
    public class User
    {
        public long Id { get; set; }
        public string Username { get; set; }
        public int Role{ get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public string[] Learning { get; set; }
        public string Speaks { get; set; }
        public long? BirthDate { get; set; }
        public string Avatar { get; set; }
        public string[] Followers { get; set; }
        public string[] Following { get; set; }
    }
}
