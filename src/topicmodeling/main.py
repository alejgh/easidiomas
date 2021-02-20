""" Minimal Flask application to test logging to Kafka """

from flask import Flask
from util.logging_handler import KafkaLoggingHandler

import json
import logging
import random


app = Flask(__name__)

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)
if len(logger.handlers) == 0:
    logger.addHandler(logging.StreamHandler())

kh = KafkaLoggingHandler("kafka:9092", key="topic_modeling")
logger.addHandler(kh)


@app.route('/')
def hello_world():
    logger.debug("Doing some expensive operation...")
    res = random.randint(5, 10)
    logger.debug(f"Finished! Result was: {res}")
    return 'Hello, World!'
