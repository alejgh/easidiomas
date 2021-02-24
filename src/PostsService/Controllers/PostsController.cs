using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using PostsService.Model;
using PostsService.Service;
using PostsService.Wrappers;

namespace PostsService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly ILogger<PostsController> logger;
        private readonly IPostsService _service;

        public PostsController(IPostsService service, ILogger<PostsController> logger)
        {
            _service = service;
            this.logger = logger;
        }

        // GET: api/Posts
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Post>>> GetPosts([FromQuery] PaginationFilter paginationFilter,
            [FromQuery] PostsFilter postsFilter, [FromQuery] SortingInfo sortingInfo)
        {
            logger.LogDebug("GET posts has been called");

            // we re-create the filters to perform internal validation (e.g. offset is greater than or equal to 0)
            var validPaginationFilter = new PaginationFilter(paginationFilter.Offset, paginationFilter.Limit);
            var validPostsFilter = new PostsFilter(postsFilter.User, postsFilter.Language);
            var validSortingInfo = new SortingInfo(sortingInfo.SortBy, sortingInfo.Order);

            IEnumerable<Post> posts = await _service.GetPosts(validPaginationFilter, validPostsFilter, validSortingInfo);
            int total = await _service.GetPostsCount(validPostsFilter);

            return Ok(new PaginatedResponse<Post>(posts, "api/posts/",
                validPaginationFilter.Limit, total, validPaginationFilter.Offset));
        }

        // GET: api/Posts/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Post>> GetPost(long id)
        {
            var post = await _service.GetPost(id);

            if (post == null)
            {
                return NotFound();
            }

            return Ok(post);
        }

        // PUT: api/Posts/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPost(long id, Post post)
        {
            if (id != post.Id) return BadRequest();

            // the following values shouldn't be updated by a user
            Post originalPost = await _service.GetPost(id);
            if (originalPost == null) return NotFound();

            post.AuthorId = originalPost.AuthorId;
            post.CreatedDate = originalPost.CreatedDate;

            try
            {
                await _service.UpdatePost(post);
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
            // TODO: verificar cómo nos llega el passport del api entrypoint
            long userID = -1;
            if (Request.Headers.TryGetValue("passport.userID", out var passportUserID)) {
                userID = Convert.ToInt64(passportUserID);
            }

            // auto created fields
            post.AuthorId = userID;
            post.CreatedDate = DateTime.Now;
            post.Likes = 0;

            await _service.CreatePost(post);

            return CreatedAtAction("GetPost", new { id = post.Id }, post);
        }

        // DELETE: api/Posts/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePost(long id)
        {
            // TODO: verificar cómo nos llega el passport del api entrypoint
            string role = "USER";
            if (Request.Headers.TryGetValue("passport.userRole", out var passportUserRole))
            {
                role = passportUserRole;
            }

            if (!role.ToUpper().Equals("ADMIN")) return Unauthorized();

            Post post = await _service.GetPost(id);
            if (post == null) return NotFound();

            await _service.DeletePost(post);
            return NoContent();
        }
    }
}
