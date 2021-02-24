using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace translationsservice.Model
{
    public class TranslationRequest
    {
        public string SourceLanguage { get; set; }
        public string TargetLanguage { get; set; }
        public string Text { get; set; }

        public TranslationRequest() { }

        public override string ToString()
        {
            return "TranslationRequest = SourceLaunguage [" + SourceLanguage + ", TargetLanguage ["
                + TargetLanguage + ", Text [" + Text + "].";
        }
    }
}
