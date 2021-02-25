""" Configuration module for the flask app.

Each class represents a configuration to use by the flask app
created in src/__init__.py.
"""

import os

from src.util.error import InvalidConfigError

def _try_get_config_from_env(config_key, default):
    if config_key not in os.environ:
        return default
    return os.environ[config_key]

class BaseConfig():
    DEBUG = False
    TESTING = False
    # these are our environment variables...
    # if they are not found, a default value is provided for local tests
    KAFKA_ENDPOINT = _try_get_config_from_env('KAFKA_ENDPOINT', 'localhost:9092')
    KAFKA_LOGGING_TOPIC = _try_get_config_from_env('KAFKA_LOGGING_TOPIC', 'service_logs')
    SERVICE_KEY = _try_get_config_from_env('SERVICE_KEY', 'tts_service')

class DevelopmentConfig(BaseConfig):
    DEBUG = True
    TESTING = True
    ENV = 'development'

class ProductionConfig(BaseConfig):
    ENV = 'production'

class TestingConfig(BaseConfig):
    TESTING = True
    ENV = 'testing'