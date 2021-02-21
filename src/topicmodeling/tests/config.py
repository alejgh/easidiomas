""" Configuration variables used in tests

See config.py file in src for more information.
"""
import os

KAFKA_ENDPOINT = os.environ.get('KAFKA_ENDPOINT') or 'localhost:9092'
KAFKA_INPUT_TOPIC = os.environ.get('INPUT_TOPIC') or 'posts'