using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.AspNetCore.Routing;

namespace WebClient.Filters
{
    public class LoginFilter : ActionFilterAttribute
    {

        public override void OnActionExecuting(ActionExecutingContext context)
        {
            if (!context.HttpContext.Session.TryGetValue("Token", out var _))
            {
                // user is not logged in... see where we are going to
                string path = context.HttpContext.Request.Path.ToString();
                if (!path.Contains("login"))
                {
                    // if we are not going to log in, redirect to login
                    context.Result = new RedirectToRouteResult(
                        new RouteValueDictionary(new { controller = "Login", action = "Login" })
                    );
                }
            }
        }
    }
}
