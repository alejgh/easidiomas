using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using WebClient.Models;
using WebClient.Services;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace WebClient.Controllers
{

    public class LoginController : Controller
    {
        private readonly LoginService _loginService;


        public LoginController(LoginService loginService)
        {
            _loginService = loginService;
        }


        // GET: /login
        [Route("/login")]
        [HttpGet]
        public IActionResult Login()
        {
            return View();
        }


        // POST: /login
        [Route("/login")]
        [HttpPost]
        public IActionResult Login([Bind("Username", "Password")] UserLoginData userData)
        {
            if (ModelState.IsValid)
            {
                // Add token to session
                LoginResult loginResult = _loginService.DoLogin(userData.Username, userData.Password);
                if (loginResult.Successful)
                {
                    HttpContext.Session.SetString("Token", loginResult.Token);
                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    // TODO: add notification data
                }
            }

            return View();
        }
    }
}
