using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using PostsService.Model;
using PostsService.Wrappers;

namespace PostsService.Service
{
    public interface IPostsService
    {

        public Task CreatePost(Post post);

        public Task DeletePost(Post post);

        public ValueTask<Post> GetPost(long id);

        public Task<List<Post>> GetPosts(PaginationFilter paginationFilter,
            PostsFilter postsFilter, SortingInfo sortingInfo);

        public Task<int> GetPostsCount(PostsFilter postsFilter);

        public Task<bool> PostExists(long id);

        public Task UpdatePost(Post post);

    }
}
