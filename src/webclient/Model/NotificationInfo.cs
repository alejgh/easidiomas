using System;
namespace WebClient.Model
{
    /// <summary>
    /// Class used to send notifications to the views.
    /// </summary>
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
