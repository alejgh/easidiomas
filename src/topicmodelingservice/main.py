from src.client.soap_client import SoapClient
from src.config import KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC, \
    KAFKA_LOGGING_TOPIC, POSTS_ENDPOINT, SERVICE_KEY
from src.model.topic_extractor import TopicExtractor
from src.util.kafka_consumer import Consumer
from src.util.logging_handler import KafkaLoggingHandler
from src.util.decorators import background

import logging


logger = logging.getLogger(SERVICE_KEY)
logger.setLevel(logging.DEBUG)


def main():
    """ Starts a kafka listener and processes messages received from it.

    Entrypoint of the application, that will receive the texts to be processed
    from a kafka queue. Each text will be passed to the topic modeling pipeline,
    and when the topics are infered they will be sent to the Posts service through
    a SOAP request.
    """
    _setup_logging()
    consumer = Consumer(KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC)

    # since there is no timeout on the consumer, this is an infinite loop on messages received from kafka
    for message in consumer:
        logger.info(f"Message received from Kafka: {message.value}")
        _on_message_received(message)
    logger.warning("Connection to Kafka was lost. Stopping program")


@background
def _on_message_received(message):
    """ Logic to process a kafka message

    This function runs in the background (see decorator implementation at utils package).
    The topics from the post are extracted, and then they are sent to the Posts service
    through a SOAP request.
    """
    post_id = message.key
    post_content = message.value.decode('utf-8')

    # we could obtain information about the language of a post.
    # for now, we expect it to come in english
    extractor = TopicExtractor()
    topics = extractor.extract_topics_from(post_content)
    _send_topics_to_service(post_id, topics)

def _send_topics_to_service(post_id, topics):
    logger.info(f"Send topics to service: {topics}")
    # TODO: enviar los resultados al servicio de posts cuando esté listo
    """
    soap_client = SoapClient(POSTS_ENDPOINT)
    soap_result = soap_client.send_topics(post_id, topics)
    if soap_result.successful:
        logger.debug("SOAP request was successful")
    else:
        logger.warning("SOAP request was unsuccessful")
    """ 

def _setup_logging():
    if len(logger.handlers) == 0:
        logger.addHandler(logging.StreamHandler())
    logger.debug(f"Starting kafka logging with endpoint '{KAFKA_ENDPOINT}' and topic '{KAFKA_LOGGING_TOPIC}'")
    kh = KafkaLoggingHandler(KAFKA_ENDPOINT, key="topic_modeling", topic=KAFKA_LOGGING_TOPIC)
    logger.addHandler(kh)
    logger.debug("Logging system started")


if __name__ == '__main__':
    exit(main())
