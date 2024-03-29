using System.Net;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using RestSharp;
using WebClient.Model.External;

namespace WebClient.Services
{
    public class UsersService
    {
        private readonly RestClient _usersClient;
        private readonly ILogger<UsersService> _logger;

        public UsersService(IConfiguration config, ILogger<UsersService> logger)
        {
            string usersEndpoint = $"{config["EASIDIOMAS_API_ENDPOINT"]}/users";
            _usersClient = new RestClient(usersEndpoint);
            _logger = logger;
        }

        public UsersPaginatedResponse GetUsers(string token)
        {
            var request = new RestRequest(Method.GET);
            request.RequestFormat = DataFormat.Json;
            request.AddHeader("token", token);

            var response = _usersClient.Execute(request);
            bool success = response.StatusCode == HttpStatusCode.OK;
            if (!success) return null;

            // rest sharp serialization was not working for complex objs like this one
            return JsonConvert.DeserializeObject<UsersPaginatedResponse>(response.Content);
        }

        public bool DeleteUser(int postId, string token)
        {
            var request = new RestRequest(postId.ToString(), Method.DELETE);
            request.RequestFormat = DataFormat.Json;
            request.AddHeader("token", token);

            var response = _usersClient.Execute(request);
            return response.StatusCode == HttpStatusCode.OK;

        }
    }
}
