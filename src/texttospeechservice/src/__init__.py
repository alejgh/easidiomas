""" src package __init__ file
"""

import logging
import os

from flask import Flask

from .util.logging_handler import KafkaLoggingHandler


CONFIG = {
    "base": "config.BaseConfig",
    "development": "config.DevelopmentConfig",
    "production": "config.ProductionConfig",
    "testing": "config.TestingConfig"
}

def create_app():
    """ Factory to create the flask application and load the config.
    By default config.BaseConfig is used. In order to change this config
    the FLASK_CONFIG environment variable must be changed to match one of
    the keys in the CONFIG dict.
    
    Returns
    -------
    app: Flask.app object
    """
    app = Flask(__name__, instance_relative_config=True)
    config_name = os.getenv('FLASK_CONFIG', 'base')
    app.config.from_object(CONFIG[config_name])
    _setup_logging(app.config['KAFKA_ENDPOINT'], app.config['KAFKA_LOGGING_TOPIC'], app.config['SERVICE_KEY'])
    with app.app_context():
        from .controller import tts
    return app

def _setup_logging(kafka_endpoint, topic, service_key):
    """ Setup of logging to a kafka endpoint """
    logger = logging.getLogger(service_key)
    logger.setLevel(logging.DEBUG)

    if len(logger.handlers) == 0:
        logger.addHandler(logging.StreamHandler())

    logger.debug(f"Starting kafka logging with endpoint '{kafka_endpoint}' and topic '{topic}'")
    kh = KafkaLoggingHandler(kafka_endpoint, key="topic_modeling", topic=topic)
    logger.addHandler(kh)
    logger.debug("Logging system started")
