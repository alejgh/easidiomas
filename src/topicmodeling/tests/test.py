""" Simple test to check that the Kafka connection is working """
from config import KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC
from util.kafka_producer import TestProducer


def main():
    producer = TestProducer(KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC)
    producer.push("Prueba de un mensaje")


if __name__ == '__main__':
    main()
