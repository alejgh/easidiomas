using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Web;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using WebClient.Models;
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
        [Route("/login")]
        [HttpGet]
        public IActionResult Login()
        {
            _logger.LogInformation("Error page from home controller has been called");
            return View();
        }


        // POST: /login
        [Route("/login")]
        [HttpPost]
        public IActionResult Login([Bind("Username", "Password")] UserLoginData userData)
        {
            _logger.LogInformation("POST controller for login has been called");

            JsonSerializerSettings settings = new JsonSerializerSettings
            {
                StringEscapeHandling = StringEscapeHandling.EscapeHtml
            };
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
    }
}
