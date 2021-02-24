using Google.Cloud.Translation.V2;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;

using translationsservice.Model;

namespace translationsservice.Controllers
{
    [ApiController]
    [Route("api/translations")]
    public class TranslationsController : ControllerBase
    {

        private readonly ILogger<TranslationsController> _logger;

        public TranslationsController(ILogger<TranslationsController> logger)
        {
            _logger = logger;
        }

        [HttpPost]
        public ActionResult<TranslationResponse> Translate([FromBody] TranslationRequest translationRequest)
        {
            _logger.LogInformation("Translation requested: " + translationRequest);
            Console.OutputEncoding = System.Text.Encoding.UTF8;
            TranslationClient client = TranslationClient.Create();
            
            var response = client.TranslateText(
                text: translationRequest.Text,
                targetLanguage: translationRequest.TargetLanguage, 
                sourceLanguage: translationRequest.SourceLanguage
            );
            var translation = response.TranslatedText;

            _logger.LogInformation("Translation completed: " + translation);

            TranslationResponse translationResponse = new TranslationResponse();
            translationResponse.SourceLanguage = translationRequest.SourceLanguage;
            translationResponse.TargetLanguage = translationRequest.TargetLanguage;
            translationResponse.Text = translationRequest.Text;
            translationResponse.Translation = translation;
            return new CreatedResult("http://translationsservice/api/translations/" + DateTime.Now.GetHashCode(), translationResponse);
        }
    }
}
