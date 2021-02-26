import csv
import logging

from flask import current_app as app


logger = logging.getLogger(app.config['SERVICE_KEY'])
logger.setLevel(logging.DEBUG)


class LocaleManager:
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
