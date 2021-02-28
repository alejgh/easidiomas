using System;
using System.ComponentModel.DataAnnotations;

namespace WebClient.Model
{
    /// <summary>
    /// Data fetched from the login view.
    /// </summary>
    public class UserLoginData
    {
        [Required]
        [StringLength(60, MinimumLength=2)]
        public string Username { get; set; }

        [Required]
        [StringLength(60, MinimumLength = 2)]
        public string Password { get; set; }
    }
}
