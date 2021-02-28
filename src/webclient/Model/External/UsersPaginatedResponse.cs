using System;
namespace WebClient.Model.External
{
    /// <summary>
    /// Workaround since users service does not return the list of users
    /// under the "data" field.
    /// </summary>
    public class UsersPaginatedResponse : BasePaginatedResponse<User>
    {
        public User[] Users { get; set; }

        public override User[] GetData()
        {
            return Users;
        }
    }
}
