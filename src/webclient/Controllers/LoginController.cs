using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebClient.Model;
using WebClient.Services;
using WebClient.Util;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace WebClient.Controllers
{

    public class LoginController : Controller
    {
        private readonly LoginService _loginService;
        private readonly ILogger<LoginController> _logger;


        public LoginController(LoginService loginService, ILogger<LoginController> logger)
        {
            _loginService = loginService;
            _logger = logger;
        }


        // GET: /login
        /// <summary>
        /// Retrieves the login view where a user can introduce his credentials.
        /// </summary>
        /// <returns></returns>
        [Route("/login")]
        [HttpGet]
        public IActionResult Login()
        {
            _logger.LogInformation("Login page from LoginController has been called");
            return View();
        }


        // POST: /login
        /// <summary>
        /// Performs a login in the system.
        ///
        /// If the login credentials do not pass model validation, or the username
        /// and password combination is not valid, an error message will be returned.
        /// Otherwise, the user's token will be stored in the headers so the user
        /// can access the system functionality.
        /// </summary>
        [Route("/login")]
        [HttpPost]
        public IActionResult Login([Bind("Username", "Password")] UserLoginData userData)
        {
            _logger.LogInformation("POST controller for login has been called");

            if (ModelState.IsValid)
            {
                // Add token to session
                _logger.LogDebug("Model state was valid. Calling login service to validate credentials");
                LoginResult loginResult = _loginService.DoLogin(userData.Username, userData.Password);
                if (loginResult.Successful)
                {
                    _logger.LogDebug($"Credentials are valid -> Adding token to session ({loginResult.Token})");
                    HttpContext.Session.SetString("Token", loginResult.Token);
                    TempData.Put("Notification", new NotificationInfo
                    {
                        Message = "Logged in successfully",
                        ClassName = "success"
                    });
                    return RedirectToAction("Index", "Home");
                }

                TempData.Put("Notification", new NotificationInfo {
                    Message="Credentials are not valid",
                    ClassName="error"
                });
            }

            return View();
        }


        // GET: /logout
        [Route("/logout")]
        [HttpGet]
        public IActionResult Logout()
        {
            _logger.LogInformation("Logout page from home controller has been called");
            HttpContext.Session.Remove("Token");
            TempData.Put("Notification", new NotificationInfo
            {
                Message = "Logged out successfully",
                ClassName = "success"
            });
            return RedirectToAction("Login", "Login");
        }
    }
}
