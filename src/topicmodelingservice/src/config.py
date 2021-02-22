""" Configuration variables used in the application.

These variables should be setup as environment variables
in the docker-compose.yml file when launching all the services.

If these environment variables are not present, default values
are asigned to them.
"""
import os

KAFKA_ENDPOINT = os.environ.get('KAFKA_ENDPOINT') or 'localhost:9092'
KAFKA_INPUT_TOPIC = os.environ.get('INPUT_TOPIC') or 'posts'
KAFKA_LOGGING_TOPIC = os.environ.get('LOGGING_TOPIC') or 'service_logs'
POSTS_ENDPOINT = os.environ.get('POSTS_SERVICE_ENDPOINT') or 'localhost:7777/soapws/posts.wsdl'
SERVICE_KEY = 'topic_modeling'