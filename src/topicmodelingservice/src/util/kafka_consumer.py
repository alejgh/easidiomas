import logging
import json
import os
import time

from kafka import KafkaConsumer, TopicPartition
from kafka.errors import NoBrokersAvailable

from ..config import SERVICE_KEY

logger = logging.getLogger(SERVICE_KEY)
logger.setLevel(logging.DEBUG)


class Consumer:
    """ Custom class that encapsulates the funcionality of a Kafka Client.

    This class can be used to listen to a given topic from kafka and fetch the
    messages sent through that topic.
    """

    def __init__(self, endpoint, topic):
        logger.debug(f"Initializing consumer on endpoint '{endpoint}' and topic '{topic}'")
        while not hasattr(self, 'consumer'):
            logger.debug("Creating the kafka consumer")
            try:
                self.consumer = KafkaConsumer(bootstrap_servers=endpoint,
                                              auto_offset_reset='latest',
                                              group_id=None)
            except NoBrokersAvailable as nba:
                logger.error("No broker was found. Retrying...")
                time.sleep(3)

        logger.debug(f"Consumer has been started!")
        self.consumer.subscribe(topic)

    def __iter__(self):
        if not self.consumer:
            raise ConnectionError("Was not able to connect to kafka")
        
        for msg in self.consumer:
            logger.debug(f"Yielding a new message from kafka consumer: {msg}")
            yield msg
