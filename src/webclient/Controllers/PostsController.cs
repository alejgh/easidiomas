using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;


namespace WebClient.Controllers
{
    public class PostsController : Controller
    {
        // GET: /<controller>/
        public IActionResult Index(bool isOffensive=false)
        {
            return View();
        }
    }
}
