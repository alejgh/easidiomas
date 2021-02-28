using System;
using Microsoft.Extensions.Configuration;
using RestSharp;
using WebClient.Models;

namespace WebClient.Services
{
    public class UsersService
    {
        private readonly RestClient _usersClient;

        public UsersService(IConfiguration config)
        {
            string usersEndpoint = $"{config["EASIDIOMAS_API_ENDPOINT"]}/users";
            _usersClient = new RestClient(usersEndpoint);
        }
    }
}
