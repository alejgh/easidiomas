using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace translationsservice.Model
{
    public class IdentificationRequest
    {
        public string TextToIdentificate { get; set; }

        public IdentificationRequest() { }

        public override string ToString()
        {
            return "IdentificationRequest = TextToIdentificate [" + TextToIdentificate + "]";
        }
    }
}
