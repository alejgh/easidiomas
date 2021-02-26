import csv

class LocaleManager:
    """ Utility class to handle locale data and transform languages to a given default locale.

    This class handles the locales supported by our tts service. It should be used to check
    if the locale provided by the user is supported. In case that a user does not send a locale,
    it can transform a language to a default locale for the language (e.g. 'en' to 'en-UK')
    """

    def __init__(self, locale_mappings_file):
        # this may as well be a singleton, since we should only read
        # the mappings once. However, due to time constraints and since
        # this is only used once in the app it suffices for now
        self.mappings = {}
        with open(locale_mappings_file, 'r', encoding='utf-8') as f:
            reader = csv.DictReader(f)
            for row in reader:
                self.mappings[row['language']] = row['locale']
    
    def is_locale(self, l):
        return l in self.mappings.values()
    
    def try_get_locale(self, language, default=None):
        return self.mappings[language] if language in self.mappings else default
