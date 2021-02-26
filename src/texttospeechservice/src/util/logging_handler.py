import logging
import time
import traceback
import sys

from kafka import KafkaProducer
from kafka.errors import NoBrokersAvailable


logger = logging.getLogger()
logger.setLevel(logging.INFO)


class KafkaLoggingHandler(logging.Handler):
    """ Custom logging handler that redirects logs to a kafka queue

    Logs will be sent under the 'service_logs' topic.
    """
    def __init__(self, endpoint, key="default", topic="default"):
        while not hasattr(self, 'producer'):
            try:
                self.producer = KafkaProducer(bootstrap_servers=endpoint)
            except NoBrokersAvailable as nba:
                logger.error("No broker was found. Retrying...")
                time.sleep(3)
        self.key = str.encode(key)
        self.topic = topic
        logging.Handler.__init__(self)

    def emit(self, record):
        if record.name == 'kafka':
            return
        try:
            msg = self.format(record)
            self.producer.send("service_logs", value=str.encode(msg), key=self.key)
        except:
            ei = sys.exc_info()
            traceback.print_exception(ei[0], ei[1], ei[2], None, sys.stderr)
            del ei

    def close(self):
        self.producer.close()
        logging.Handler.close(self)
