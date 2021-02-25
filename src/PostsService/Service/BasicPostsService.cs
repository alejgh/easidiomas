using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Confluent.Kafka;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using PostsService.Data;
using PostsService.Kafka;
using PostsService.Model;
using PostsService.Wrappers;

namespace PostsService.Service
{
    public class BasicPostsService : IPostsService
    {
        private readonly DataContext _context;
        private readonly ILogger<BasicPostsService> _logger;

        public BasicPostsService(DataContext context, ILogger<BasicPostsService> logger)
        {
            _context = context;
            _logger = logger;
        }

        /// <inheritdoc/>
        public Task CreatePost(Post post)
        {
            _logger.LogDebug($"Creating post from service: {post}");
            _context.Posts.Add(post);

            return _context.SaveChangesAsync();
        }

        /// <inheritdoc/>
        public Task DeletePost(Post post)
        {
            _logger.LogDebug($"Deleting post from service: {post}");
            _context.Posts.Remove(post);
            return _context.SaveChangesAsync();
        }

        /// <inheritdoc/>
        public ValueTask<Post> GetPost(long id)
        {
            _logger.LogDebug($"Retrieving post from service with id '{id}'");
            return _context.Posts.FindAsync(id);
        }

        /// <inheritdoc/>
        public Task<List<Post>> GetPosts(PaginationFilter paginationFilter, PostsFilter postsFilter, SortingInfo sortingInfo)
        {
            _logger.LogDebug("Retrieving list of posts from service.");
            _logger.LogDebug($"Pagination Data: {paginationFilter}");
            _logger.LogDebug($"Data filters: {postsFilter}");
            _logger.LogDebug($"Sorting parameters: {sortingInfo}");

            // handling multiple languages as a filter (e.g. 'en|ko|es')
            IList<string> languages = new List<string>();
            if (postsFilter.Language != null ) languages = postsFilter.Language.Split('|');

            // we execute filters and get total results
            IQueryable<Post> postsQueryable = _context.Posts
                .Where(p => postsFilter.User == null ? true : p.AuthorId == postsFilter.User)
                .Where(p => postsFilter.Language == null ? true : languages.Contains(p.Language));

            // now pagination
            postsQueryable = postsQueryable
                .Skip(paginationFilter.Offset)
                .Take(paginationFilter.Limit);

            // sorting...
            // at this point the name of the property has been validated to exist
            if (sortingInfo.Order.Equals("asc"))
                postsQueryable = postsQueryable.OrderBy(p => EF.Property<object>(p, sortingInfo.SortBy));
            else
                postsQueryable = postsQueryable.OrderByDescending(p => EF.Property<object>(p, sortingInfo.SortBy));

            // and get the posts!!
            return postsQueryable.ToListAsync();
        }

        /// <inheritdoc/>
        public Task<int> GetPostsCount(PostsFilter postsFilter)
        {
            _logger.LogDebug("Retrieving number of posts from service");

            // we execute filters and get total results
            IQueryable<Post> postsQueryable = _context.Posts
                .Where(p => postsFilter.User == null ? true : p.AuthorId == postsFilter.User)
                .Where(p => postsFilter.Language == null ? true : p.Language == postsFilter.Language);
            return postsQueryable.CountAsync();
        }

        /// <inheritdoc/>
        public Task<bool> PostExists(long id)
        {
            return _context.Posts.AnyAsync(post => post.Id == id);
        }

        /// <inheritdoc/>
        public Task UpdatePost(Post post)
        {
            _logger.LogDebug($"Updating post from service: {post}");

            // the following values shouldn't be updated by a user
            post.AuthorId = post.AuthorId;
            post.CreatedDate = post.CreatedDate;

            _context.Entry(post).State = EntityState.Modified;
            return _context.SaveChangesAsync();
        }

        /// <inheritdoc/>
        public Task UpdatePost(Post originalPost, Post finalPost)
        {
            _logger.LogDebug($"Updating post from service: {finalPost}");

            _context.Entry(originalPost).State = EntityState.Detached;

            return UpdatePost(finalPost);
        }
    }
}
