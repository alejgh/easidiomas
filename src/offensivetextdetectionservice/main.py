from src.config import KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC, \
    KAFKA_LOGGING_TOPIC, SERVICE_KEY
from src.kafka.kafka_consumer import Consumer
from src.kafka.kafka_producer import Producer
from src.util.logging_handler import KafkaLoggingHandler
from src.util.decorators import background

from profanity_check import predict, predict_prob


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
    producer = Producer(KAFKA_ENDPOINT)

    # since there is no timeout on the consumer, this is an infinite loop on messages received from kafka
    for message in consumer:
        logger.info(f"Message received from Kafka: {message.value}")
        _on_message_received(message, producer)
    logger.warning("Connection to Kafka was lost. Stopping program")


@background
def _on_message_received(message, producer):
    """ Logic to process a kafka message

    This function runs in the background (see decorator implementation at utils package).
    The topics from the post are extracted, and then they are sent to the Posts service
    through a SOAP request.
    """
    post_id = message.key
    post_content = message.value.decode('utf-8')

    # we could obtain information about the language of a post.
    # for now, we expect it to come in english
    logger.debug(f"Predicting offensiveness of post: {post_content}")
    is_offensive = predict([post_content])[0]
    logger.debug(f"Result: {is_offensive}")
    _send_offensive_to_queue(post_id, is_offensive, producer)

def _send_offensive_to_queue(post_id, is_offensive, producer):
    logger.info(f"Send is_offensive to queue: {is_offensive}")
    producer.produce("offensive_text_detection", post_id, bytes(is_offensive))

def _setup_logging():
    if len(logger.handlers) == 0:
        logger.addHandler(logging.StreamHandler())
    logger.debug(f"Starting kafka logging with endpoint '{KAFKA_ENDPOINT}' and topic '{KAFKA_LOGGING_TOPIC}'")
    kh = KafkaLoggingHandler(KAFKA_ENDPOINT, key="offensive_text_detection", topic=KAFKA_LOGGING_TOPIC)
    logger.addHandler(kh)
    logger.debug("Logging system started")


if __name__ == '__main__':
    exit(main())
