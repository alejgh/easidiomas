using System;
using System.Net;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using RestSharp;
using WebClient.Model.External;

namespace WebClient.Services
{
    public class StatisticsService
    {
        private readonly RestClient _statisticsClient;
        private readonly ILogger<StatisticsService> _logger;

        public StatisticsService(IConfiguration config, ILogger<StatisticsService> logger)
        {
            string statisticsEndpoint = $"{config["EASIDIOMAS_API_ENDPOINT"]}/statistics";
            _statisticsClient = new RestClient(statisticsEndpoint);
            _logger = logger;
        }

        public Statistics GetStatistics(string token)
        {
            var request = new RestRequest(Method.GET);
            request.RequestFormat = DataFormat.Json;
            request.AddHeader("token", token);

            var response = _statisticsClient.Execute(request);
            bool success = response.StatusCode == HttpStatusCode.OK;
            if (!success) return null;

            // rest sharp serialization was not working for complex objs like this one
            return JsonConvert.DeserializeObject<Statistics>(response.Content);
        }
    }
}
