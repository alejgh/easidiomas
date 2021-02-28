using System;
using System.Net;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using RestSharp;
using WebClient.Model;

namespace WebClient.Services
{
    public class LoginService
    {
        private readonly RestClient _loginClient;
        private readonly ILogger<LoginService> _logger;

        public LoginService(IConfiguration config, ILogger<LoginService> logger)
        {
            _logger = logger;
            string loginEndpoint = $"{config["EASIDIOMAS_API_ENDPOINT"]}/auth/token";
            _logger.LogDebug($"Login Endpoint: {loginEndpoint}");
            _loginClient = new RestClient(loginEndpoint);
        }

        public LoginResult DoLogin(UserLoginData loginData)
        {
            var request = new RestRequest(Method.POST);
            request.RequestFormat = DataFormat.Json;
            request.AddJsonBody(loginData);

            var response = _loginClient.Execute<dynamic>(request);
            bool success = response.StatusCode == HttpStatusCode.Created;
            if (!success)
                return new LoginResult() { Successful = false, Token = "", Permissions = "-1" };

            return new LoginResult() {
                Successful = success,
                Token = response.Data["tokenGenerated_"],
                Permissions = response.Data["tokenPermissions_"]
            };
        }
    }
}
