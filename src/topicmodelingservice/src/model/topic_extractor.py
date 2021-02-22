import logging
import operator

import nltk
import numpy as np

from collections import defaultdict
from nltk import tokenize
from nltk.corpus import stopwords

from ..config import SERVICE_KEY


logger = logging.getLogger(SERVICE_KEY)
logger.setLevel(logging.DEBUG)


class TopicExtractor:
    """ Really naive implementation of a topic extraction algorithm.

    This class encapsulates an algorithm that uses the TF-IDF of a document
    to fetch the n most important words of a given text.

    This should not be used in a real-life application, but for the purposes
    of the subject it's sufficient.
    """

    def __init__(self, language='english', num_topics=5):
        self.stop_words = set(stopwords.words('english'))
        self.num_topics = num_topics

    def extract_topics_from(self, text):
        logger.debug(f"Extracting topics for text: {text}")
        tokens = self._preprocess_text(text)
        sentences = [s.lower() for s in tokenize.sent_tokenize(text)]
        tf_idf_scores = calculate_tf_idf(tokens, sentences)
        topics = self._get_top_topics(tf_idf_scores)
        logger.debug(f"Topics: {topics}")
        return topics
    
    def _get_top_topics(self, elements):
        return sorted(elements.items(), key=operator.itemgetter(1), reverse=True)[:self.num_topics]

    def _preprocess_text(self, text):
        # preprocessing of texts: tokenization -> remove non alphanumeric tokens ->
        # -> remove stop words -> get only nouns with more than 2 chars
        logger.debug("Preprocessing text")
        is_noun = lambda pos: pos[:2] == 'NN'
        tokens = tokenize.word_tokenize(text)
        alphabetic_tokens = [t for t in tokens if t.isalpha()]
        tokens_without_stopwords = [t for t in tokens if t not in self.stop_words]
        final_tokens = [word for (word, pos) in nltk.pos_tag(tokens_without_stopwords)
                        if len(word) > 2 and is_noun(pos)]
        logger.debug(f"Tokens of text: {final_tokens}")
        return final_tokens
    
def calculate_tf_idf(tokens, sentences):
    logger.debug("Calculating TF-IDF of tokens")
    tf_scores = _calculate_tf(tokens)
    idf_scores = _calculate_idf(tokens, sentences)
    tf_idf_scores = {word: tf_scores[word] * idf_scores[word] for word in tokens}
    logger.debug(f"TF-IDF obtained: {tf_idf_scores}")
    return tf_idf_scores

def _calculate_tf(tokens):
    logger.debug("Calculating TF of tokens")
    tf_scores = defaultdict(lambda: 0)
    num_words = len(tokens)
    for token in tokens:
        tf_scores[token] += 1
    logger.debug(f"TF scores: {tf_scores}")
    normalized_tf_scores = {token: value/num_words for token, value in tf_scores.items()}
    logger.debug(f"Normalized TF scores for document: {normalized_tf_scores}")
    return normalized_tf_scores

def _calculate_idf(tokens, sentences):
    logger.debug("Calculating IDF of tokens")
    token_documents = defaultdict(set)
    total_sentences = len(sentences)
    for idx, sentence in enumerate(sentences):
        for token in tokens:
            if token.lower() in sentence:
                token_documents[token].add(idx)
    df_scores = {token: len(documents) if len(documents) > 0 else 1
                 for token, documents in token_documents.items()}
    logger.debug(f"DF scores for document: {df_scores}")
    idf_scores = {token: np.log(total_sentences / df) for token, df in df_scores.items()}
    logger.debug(f"IDF scores for document: {idf_scores}")
    return idf_scores
