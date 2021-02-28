using System;
using System.Net;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using RestSharp;
using WebClient.Model.External;

namespace WebClient.Services
{
    public class PostsService
    {
        private readonly RestClient _postsClient;
        private readonly ILogger<PostsService> _logger;

        public PostsService(IConfiguration config, ILogger<PostsService> logger)
        {
            string postsEndpoint = $"{config["EasidiomasAPIEndpoint"]}/posts";
            _postsClient = new RestClient(postsEndpoint);
            _logger = logger;
        }

        public PaginatedResponse<Post> GetPosts(int offset, int limit, string token)
        {
            var request = new RestRequest(Method.GET);
            request.RequestFormat = DataFormat.Json;
            request.AddHeader("token", token);
            request.AddQueryParameter("offset", offset.ToString());
            request.AddQueryParameter("limit", limit.ToString());

            var response = _postsClient.Execute(request);
            bool success = response.StatusCode == HttpStatusCode.OK;
            if (!success) return null;

            // rest sharp serialization was not working for complex objs like this one
            return JsonConvert.DeserializeObject<PaginatedResponse<Post>>(response.Content);
        }

        public bool DeletePost(int postId, string token)
        {
            var request = new RestRequest(postId.ToString(), Method.DELETE);
            request.RequestFormat = DataFormat.Json;
            request.AddHeader("token", token);

            var response = _postsClient.Execute(request);
            return response.StatusCode == HttpStatusCode.NoContent;
        }
    }
}
