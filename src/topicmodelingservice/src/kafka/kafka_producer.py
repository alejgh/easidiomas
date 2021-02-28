import logging
import time
import traceback
import sys

from kafka import KafkaProducer
from kafka.errors import NoBrokersAvailable

from ..config import SERVICE_KEY

logger = logging.getLogger(SERVICE_KEY)
logger.setLevel(logging.DEBUG)


class Producer():
    """ Custom logging handler that redirects logs to a kafka queue

    Logs will be sent under the 'service_logs' topic.
    """
    def __init__(self, endpoint):
        while not hasattr(self, 'producer'):
            try:
                self.producer = KafkaProducer(bootstrap_servers=endpoint)
            except NoBrokersAvailable as nba:
                logger.error("No broker was found. Retrying...")
                time.sleep(3)

    def produce(self, topic, key, msg):
        try:
            logger.debug(f"Sending message to kafka queue[key={key}, msg={msg}]")
            self.producer.send(topic, value=msg, key=key)
        except:
            ei = sys.exc_info()
            print(ei)
            traceback.print_exception(ei[0], ei[1], ei[2], None, sys.stderr)
            del ei

