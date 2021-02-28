using System;
using WebClient.Model.External;

namespace WebClient.Model
{
    /// <summary>
    /// This class is used to encapsulate the paginated results obtained
    /// from the REST APIs being fetched. It adds an additional offset field
    /// that is used by the views to implement pagination more easily.
    /// </summary>
    public class ModelData<T>
    {
        public int Offset { get; set; }
        public int Count { get; set; }
        public int Total { get; set; }
        public T[] Data { get; set; }

        public ModelData(BasePaginatedResponse<T> paginatedResponse, int offset)
        {
            Offset = offset;
            Count = paginatedResponse.Count;
            Total = paginatedResponse.Total;
            Data = paginatedResponse.GetData();
        }
    }
}
