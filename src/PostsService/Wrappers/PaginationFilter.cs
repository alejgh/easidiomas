namespace PostsService.Wrappers
{
    public class PaginationFilter
    {
        public int Offset { get; set; }
        public int Limit { get; set; }

        public PaginationFilter()
        {
            Offset = 0;
            Limit = 5;
        }

        public PaginationFilter(int offset, int limit)
        {
            Offset = offset >= 0 ? offset : 0;
            Limit = limit <= 100 ? limit : 5;
        }

        public override string ToString()
        {
            return $"Pagination Filter[offset={Offset} - limit={Limit}]";
        }
    }
}
