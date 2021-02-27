using System;
namespace WebClient.Models
{
    [Serializable]
    public class NotificationInfo
    {
        public string Message { get; set; }
        public string ClassName { get; set; }

        public NotificationInfo()
        {
        }
    }
}
