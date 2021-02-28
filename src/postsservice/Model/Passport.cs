using System;
namespace PostsService.Model
{
    public class Passport
    {
        public string UserId { get; set; }
        public string Username { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public string Email { get; set; }
        public int Role { get; set; }
        public string AvatarUrl { get; set; }
        public string ExpirationDate { get; set; }
    }
}
