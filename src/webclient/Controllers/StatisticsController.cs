using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebClient.Model;
using WebClient.Model.External;
using WebClient.Services;
using WebClient.Util;

namespace WebClient.Controllers
{
    public class StatisticsController : Controller
    {
        private readonly StatisticsService _statisticsService;
        private readonly ILogger<StatisticsController> _logger;


        public StatisticsController(StatisticsService statisticsService,
            ILogger<StatisticsController> logger)
        {
            _statisticsService = statisticsService;
            _logger = logger;
        }

        // GET: /<controller>/
        /// <summary>
        /// Obtain statistics from the system.
        ///
        /// If there is no token, there will be a redirect to the login page.
        /// </summary>
        public IActionResult Index()
        {
            _logger.LogInformation("Index of statistics has been called");
            if (HttpContext.Session.TryGetValue("Token", out var token))
            {
                Statistics statistics = _statisticsService.GetStatistics(
                    System.Text.Encoding.Default.GetString(token));
                _logger.LogDebug("Statistics fetched from service");
                return View(statistics);
            }

            _logger.LogWarning("Token for user has expired. Returning to login");
            TempData.Put("Notification", new NotificationInfo
            {
                Message = "Token has expired",
                ClassName = "error"
            });
            return RedirectToAction("Login", "Login");
        }
    }
}
