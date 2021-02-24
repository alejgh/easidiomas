using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using PostsService.Data;
using PostsService.Model;
using PostsService.Wrappers;

namespace PostsService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly DataContext _context;
        private readonly ILogger<PostsController> _logger;

        public PostsController(DataContext context, ILogger<PostsController> logger)
        {
            _context = context;
            _logger = logger;
        }

        // GET: api/Posts
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Post>>> GetPosts([FromQuery] PaginationFilter paginationFilter,
            [FromQuery] PostsFilter postsFilter, [FromQuery] SortingInfo sortingInfo)
        {
            // we re-create the filters to perform internal validation (e.g. offset is greater than or equal to 0)
            var validPaginationFilter = new PaginationFilter(paginationFilter.Offset, paginationFilter.Limit);
            var validPostsFilter = new PostsFilter(postsFilter.User, postsFilter.Language);
            var validSortingInfo = new SortingInfo(sortingInfo.SortBy, sortingInfo.Order);

            // we execute filters and get total results
            IQueryable<Post> postsQueryable = _context.Posts
                .Where(p => validPostsFilter.User == null ? true : p.AuthorId == validPostsFilter.User)
                .Where(p => validPostsFilter.Language == null ? true : p.Language == validPostsFilter.Language);
            int total = await postsQueryable.CountAsync();

            // now pagination
            postsQueryable = postsQueryable
                .Skip(validPaginationFilter.Offset)
                .Take(validPaginationFilter.Limit);

            // sorting...
            // at this point the name of the property has been validated to exist
            if (validSortingInfo.Order.Equals("asc"))
                postsQueryable = postsQueryable.OrderBy(p => EF.Property<object>(p, validSortingInfo.SortBy));
            else
                postsQueryable = postsQueryable.OrderByDescending(p => EF.Property<object>(p, validSortingInfo.SortBy));

            // and get the posts!!
            IEnumerable<Post> posts = await postsQueryable.ToListAsync();

            return Ok(new PaginatedResponse<Post>(posts, "api/posts/",
                validPaginationFilter.Limit, total, validPaginationFilter.Offset));
        }

        // GET: api/Posts/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Post>> GetPost(long id)
        {
            var post = await _context.Posts.FindAsync(id);

            if (post == null)
            {
                return NotFound();
            }

            return post;
        }

        // PUT: api/Posts/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutPost(long id, Post post)
        {
            if (id != post.Id)
            {
                return BadRequest();
            }

            _context.Entry(post).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PostExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Posts
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Post>> PostPost(Post post)
        {
            post.CreatedDate = DateTime.Now;
            post.Likes = 0;

            _context.Posts.Add(post);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetPost", new { id = post.Id }, post);
        }

        // DELETE: api/Posts/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeletePost(long id)
        {
            var post = await _context.Posts.FindAsync(id);
            if (post == null)
            {
                return NotFound();
            }

            _context.Posts.Remove(post);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool PostExists(long id)
        {
            return _context.Posts.Any(e => e.Id == id);
        }
    }
}
