﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebClient.Model;
using WebClient.Model.External;
using WebClient.Services;
using WebClient.Util;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace WebClient.Controllers
{
    public class UsersController : Controller
    {
        private readonly UsersService _usersService;
        private readonly ILogger<UsersController> _logger;


        public UsersController(UsersService usersService, ILogger<UsersController> logger)
        {
            _usersService = usersService;
            _logger = logger;
        }

        // GET: /<controller>/
        public IActionResult Index(int offset = 0, int limit = 5)
        {
            if (HttpContext.Session.TryGetValue("Token", out var token))
            {
                UsersPaginatedResponse users = _usersService.GetUsers(
                    System.Text.Encoding.Default.GetString(token));

                _logger.LogDebug(users.ToString());
                return View(new ModelData<User>(users, offset));
            }

            TempData.Put("Notification", new NotificationInfo
            {
                Message = "Token has expired",
                ClassName = "error"
            });
            return RedirectToAction("Login", "Login");
        }

        // POST: Users/Delete/5
        public IActionResult Delete(int? id)
        {
            if (id == null)
            {
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "User does not exist",
                    ClassName = "error"
                });
                return RedirectToAction("Index");
            }


            bool hasToken = HttpContext.Session.TryGetValue("Token", out var token);
            if (!hasToken) return RedirectToAction("Login", "Login");

            bool success = _usersService.DeleteUser(id.Value,
                    System.Text.Encoding.Default.GetString(token));
            if (success)
            {
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "User was deleted successfully",
                    ClassName = "success"
                });
            }
            else
            {
                TempData.Put("Notification", new NotificationInfo
                {
                    Message = "There was an error deleting the user",
                    ClassName = "error"
                });
            }
            return RedirectToAction("Index");
        }
    }
}
