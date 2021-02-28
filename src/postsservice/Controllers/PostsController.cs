using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Confluent.Kafka;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using PostsService.Kafka;
using PostsService.Model;
using PostsService.Service;
using PostsService.Wrappers;

namespace PostsService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly ILogger<PostsController> _logger;
        private readonly IPostsService _service;
        private readonly KafkaProducer<long, string> _producer;

        public PostsController(IPostsService service, ILogger<PostsController> logger,
            KafkaProducer<long, string> producer)
        {
            _service = service;
            _logger = logger;
            _producer = producer;
        }

        // GET: api/Posts
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Post>>> GetPosts([FromQuery] PaginationFilter paginationFilter,
            [FromQuery] PostsFilter postsFilter, [FromQuery] SortingInfo sortingInfo)
        {
            _logger.LogInformation("GET posts has been called");

            // we re-create the filters to perform internal validation (e.g. offset is greater than or equal to 0)
            var validPaginationFilter = new PaginationFilter(paginationFilter.Offset, paginationFilter.Limit);
            var validPostsFilter = new PostsFilter(postsFilter.User, postsFilter.Language);
            var validSortingInfo = new SortingInfo(sortingInfo.SortBy, sortingInfo.Order);

            _logger.LogInformation("Calling service to retrieve list of posts and total count...");
            IEnumerable<Post> posts = await _service.GetPosts(validPaginationFilter, validPostsFilter, validSortingInfo);
            int total = await _service.GetPostsCount(validPostsFilter);
            _logger.LogDebug($"Posts fetched: {posts}");
            _logger.LogDebug($"Total count: {total}");

            return Ok(new PaginatedResponse<Post>(posts, "api/posts/",
                validPaginationFilter.Limit, total, validPaginationFilter.Offset));
        }

        // GET: api/Posts/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Post>> GetPost(long id)
        {
            _logger.LogInformation($"GET post with id '{id}' has been called");

            _logger.LogInformation("Calling service to retrieve post...");
            Post post = await _service.GetPost(id);

            if (post == null)
            {
                _logger.LogDebug("Post wasn't found, returning 404");
                return NotFound();
            }

            _logger.LogDebug($"Post was found: {post}");
            return Ok(post);
        }

        // PUT: api/Posts/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPost(long id, Post post)
        {
            _logger.LogInformation($"PUT for post with id '{id} has been called with data: {post}");

            long? userID = RetrieveUserIdFromPassport();
            if (userID == null) return BadRequest();
            else if (id != post.Id) return BadRequest();

            _logger.LogInformation($"Calling service to fetch post with id '{id}'");
            Post originalPost = await _service.GetPost(id);
            if (originalPost == null) return NotFound();
            else if (userID.Value != originalPost.AuthorId) return Unauthorized(); // a user can't modify posts made by others!

            try
            {
                _logger.LogInformation("Calling service to update post...");
                await _service.UpdatePost(originalPost, post);
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!await _service.PostExists(id)) return NotFound();
                else throw;
            }

            return NoContent();
        }

        // POST: api/Posts
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Post>> PostPost(Post post)
        {
            _logger.LogInformation($"POST for post has been called with data: {post}");

            long? userID = RetrieveUserIdFromPassport();
            if (userID == null) return BadRequest();

            // auto created fields
            post.AuthorId = userID.Value;
            post.CreatedDate = DateTime.Now;
            post.Likes = 0;
            post.Id = 0;

            _logger.LogInformation("Calling service to create post...");
            await _service.CreatePost(post);

            _logger.LogDebug($"Notifying Kafka queue of new post");
            _producer.Produce("posts", new Message<long, string>()
            {
                Key = post.Id,
                Value = post.Content
            });

            return CreatedAtAction("GetPost", new { id = post.Id }, post);
        }

        // DELETE: api/Posts/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePost(long id)
        {
            _logger.LogInformation($"DELETE for post with id '{id}' has been called");

            _logger.LogDebug("Retrieving user role from headers");
            int role = 0;
            if (Request.Headers.TryGetValue("passport", out var passportStr))
            {
                _logger.LogDebug($"Passport is present in header: {passportStr}");
                Passport passport = JsonConvert.DeserializeObject<Passport>(passportStr);
                _logger.LogDebug($"Passport loaded: {passport}");
                // TODO: CHANGE
                role = 1;
            }

            _logger.LogDebug($"User role is '{role}'");

            if (role != 1) return Unauthorized();

            Post post = await _service.GetPost(id);
            if (post == null) return NotFound();

            _logger.LogInformation("Calling service to delete post...");
            await _service.DeletePost(post);
            return NoContent();
        }

        private long? RetrieveUserIdFromPassport()
        {
            // TODO: verificar cómo nos llega el passport del api entrypoint
            _logger.LogDebug("Retrieving user id from headers");
            long? userID = null;
            if (Request.Headers.TryGetValue("passport.userID", out var passportUserID))
            {
                userID = Convert.ToInt64(passportUserID);
            }
            _logger.LogDebug($"User id is '{userID}'");
            return userID;
        }
    }
}
