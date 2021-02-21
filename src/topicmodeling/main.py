from src.config import KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC, KAFKA_LOGGING_TOPIC
from src.util.kafka_consumer import Consumer
from src.util.logging_handler import KafkaLoggingHandler

import logging


logger = logging.getLogger(__name__)
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
    logger.error("Connection to Kafka was lost. Stopping program")


def _on_message_received(message):
    # TODO: procesar mensaje
    #post_id = message.key
    #post_content = message.value
    #topics = extract_topics(post_content)
    _send_topics_to_service(post_id, topics)
    pass

def _send_topics_to_service(post_id, topics):
    logger.info("Send topics to service: ", topics)
    # TODO: enviar los resultados al servicio de posts
    #soap_client = SoapClient()
    #soap_client.send_topics(post_id, topics)

def _setup_logging():
    if len(logger.handlers) == 0:
        logger.addHandler(logging.StreamHandler())
    logger.debug(f"Starting kafka logging with endpoint '{KAFKA_ENDPOINT}' and topic '{KAFKA_LOGGING_TOPIC}'")
    kh = KafkaLoggingHandler(KAFKA_ENDPOINT, key="topic_modeling", topic=KAFKA_LOGGING_TOPIC)
    logger.addHandler(kh)
    logger.debug("Logging system started")


if __name__ == '__main__':
    exit(main())
