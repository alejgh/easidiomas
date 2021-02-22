""" Simple test to check that the Kafka connection is working """
from config import KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC
from util.kafka_producer import TestProducer


def main():
    producer = TestProducer(KAFKA_ENDPOINT, KAFKA_INPUT_TOPIC)
    producer.push("""Suppose we have a set of English text documents and wish to determine which document is most relevant to the query "the brown cow." A simple way to start out is by eliminating documents that do not contain all three words "the," "brown," and "cow," but this still leaves many documents. To further distinguish them, we might count the number of times each term occurs in each document and sum them all together; the number of times a term occurs in a document is called its term frequency. However, because the term "the" is so common, this will tend to incorrectly emphasize documents which happen to use the word "the" more frequently, without giving enough weight to the more meaningful terms "brown" and "cow". Also the term "the" is not a good keyword to distinguish relevant and non-relevant documents and terms. On the contrary, the words "brown" and "cow" that occur rarely are good keywords to distinguish relevant documents from the non-relevant documents. Hence an inverse document frequency factor is incorporated which diminishes the weight of terms that occur very frequently in the collection and increases the weight of terms that occur rarely.""")


if __name__ == '__main__':
    main()
