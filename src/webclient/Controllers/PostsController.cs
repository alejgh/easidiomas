using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebClient.Model;
using WebClient.Model.External;
using WebClient.Services;
using WebClient.Util;

namespace WebClient.Controllers
{
    /// <summary>
    /// Controller that handles calls to the Posts functionality of the system.
    ///
    /// Currently users can see a list of posts and delete posts.
    /// </summary>
    public class PostsController : Controller
    {
        private readonly PostsService _postsService;
        private readonly ILogger<PostsController> _logger;


        public PostsController(PostsService postsService, ILogger<PostsController> logger)
        {
            _postsService = postsService;
            _logger = logger;
        }

        // GET: /<controller>/
        /// <summary>
        /// Obtain a list of posts from the system.
        ///
        /// If there is no token, there will be a redirect to the login page.
        /// </summary>
        /// <param name="offset">Offset used to fetch the posts. For example, with an
        /// offset of 2, the first 2 posts returned by the system will be skipped.
        /// By default, offset is 0.</param>
        /// <param name="limit">Number of posts to be returned in each page. By
        /// default, 5 posts are returned per page.</param>
        public IActionResult Index(int offset=0, int limit=5)
        {
            _logger.LogInformation($"Index of posts has been called. [offset={offset}, limit={limit}]");
            if (HttpContext.Session.TryGetValue("Token", out var token)) {
                PaginatedResponse<Post> posts = _postsService.GetPosts(offset, limit, token.ToString());
                _logger.LogDebug("Posts fetched from service");
                return View(new ModelData<Post>(posts, offset));
            }

            _logger.LogWarning("Token for user has expired. Returning to login");
            TempData.Put("Notification", new NotificationInfo
            {
                Message = "Token has expired",
                ClassName = "error"
            });
            return RedirectToAction("Login", "Login");
        }

        // POST: Posts/Delete/5
        /// <summary>
        /// Deletes a post from the system.
        ///
        /// If there is no post for the given id or the token has expired,
        /// an error notification will be returned to the view.
        /// </summary>
        /// <param name="id">Id of the post to be deleted.</param>
        public IActionResult Delete(int? id)
        {
            _logger.LogInformation($"Delete user with id '{id}' has been called");
            if (id == null)
            {
                _logger.LogDebug("Post does not exist...");
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "Post does not exist",
                    ClassName = "error"
                });
                return RedirectToAction("Index");
            }


            bool hasToken = HttpContext.Session.TryGetValue("Token", out var token);
            if (!hasToken) return RedirectToAction("Login", "Login");

            bool success = _postsService.DeletePost(id.Value, token.ToString());
            if (success)
            {
                _logger.LogDebug("Post deleted successfully");
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "Post was deleted successfully",
                    ClassName = "success"
                });
            }
            else
            {
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "There was an error deleting the post",
                    ClassName = "error"
                });
            }
            return RedirectToAction("Index");
        }
    }
}
