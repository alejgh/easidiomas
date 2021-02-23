using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mime;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace PostsService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ExampleController : ControllerBase
    {
        ILogger<ExampleController> logger;

        public ExampleController(ILogger<ExampleController> logger)
        {
            this.logger = logger;
            logger.LogInformation("Example Controller initialized");
        }

        // api/Example
        [HttpGet]
        public dynamic SayHello()
        {
            logger.LogInformation("Say hello was called!");
            return new { Text = "Hello, World!" };
        }
    }
}
