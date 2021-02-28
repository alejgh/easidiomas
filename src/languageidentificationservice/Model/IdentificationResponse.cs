using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace translationsservice.Model
{
    public class IdentificationResponse
    {
        public string SourceLanguage { get; set; }
        public string TargetLanguage { get; set; }
        public string Text { get; set; }
        public string Translation { get; set; }

        public IdentificationResponse() { }
    }
}
