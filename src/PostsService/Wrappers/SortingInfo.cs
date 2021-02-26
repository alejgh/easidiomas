using System;
using PostsService.Model;

namespace PostsService.Wrappers
{
    public class SortingInfo
    {
        public string SortBy { get; set; }
        public string Order { get; set; }

        public SortingInfo()
        {
            SortBy = "Id";
            Order = "asc";
        }

        public SortingInfo(string sortBy, string order)
        {
            // we check with reflection if the given sort by property exists in posts
            if (typeof(Post).GetProperty(sortBy) != null) SortBy = sortBy;
            else SortBy = "Id";

            if (order != null && (order == "asc" || order == "desc")) Order = order;
            else Order = "asc";
        }

        public override string ToString()
        {
            return $"Sorting Info[sortBy={SortBy} - order={Order}]";
        }
    }
}
