import json
import os
import time
import logging

from kafka import KafkaProducer
from kafka.errors import NoBrokersAvailable


class TestProducer:
    def __init__(self, endpoint, topic):
        self.topic = topic
        while not hasattr(self, 'producer'):
            try:
                self.producer = KafkaProducer(bootstrap_servers=endpoint)
            except NoBrokersAvailable as err:
                print("Unable to find a broker. Retrying...")
                time.sleep(3)

    def push(self, message):
        print("Publishing: ", message)
        self.producer.send(self.topic, bytes(json.dumps(message).encode('utf-8')))
        self.producer.flush()
