using System;
using System.ComponentModel.DataAnnotations;

namespace WebClient.Model
{
    /// <summary>
    /// Data fetched from the login view, used 
    /// </summary>
    public class UserLoginData
    {
        [Required]
        [StringLength(60, MinimumLength = 2)]
        public string username { get; set; }

        [Required]
        [StringLength(60, MinimumLength = 2)]
        public string password { get; set; }
    }
}
