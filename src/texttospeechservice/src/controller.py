""" Controllers of the text to speech service.
"""

import base64
import json
import logging

from flask import current_app as app
from flask import jsonify, request, send_file

from .clients import GCloudTTSClient, SoapClient
from .util.error import TooManyRequestsError
from .util.locale_manager import LocaleManager


logger = logging.getLogger(app.config['SERVICE_KEY'])
logger.setLevel(logging.DEBUG)

gcloud_tts_client = GCloudTTSClient()
statistics_client = SoapClient(app.config['STATISTICS_SERVICE_ENDPOINT'])
locale_manager = LocaleManager(app.config['LOCALE_MAPPINGS_FILE'])


@app.errorhandler(404)
def page_not_found(e):
    # override default html response
    return jsonify({
        'response': 'Page not found'
    }), 404

@app.route('/api/TextsToSpeechs', methods=['POST'])
def tts():
    """ Transforms the given text to an audio.

    The returned audio is encoded as a base64 string.
    """
    logger.debug("POST to TextsToSpeechs received")
    body_data = request.get_json()
    if 'passport' in request.headers:
        logger.debug("Passport found")
        passport = json.loads(request.headers.get('passport'))
        logger.debug(f"Passport: {passport}")
        user_id = passport['userId_']
    else:
        user_id = "1"
    logger.debug(f"User ID: {user_id}")
    logger.debug(f"Data: {body_data}")

    if 'language' not in body_data or 'text' not in body_data:
        logger.debug("Some of the required information is not present, returning bad request.")
        return jsonify({
            'response': 'Not all required parameters are present in body'
        }), 400  # BadRequest
    
    try:
        language = body_data['language']
        logger.debug(f"Transforming language '{language}' to valid locale")
        locale = language if locale_manager.is_locale(language) \
                          else locale_manager.try_get_locale(language, 'en-US')
        logger.debug(f"Locale after transformation: '{locale}'")

        logger.debug("Calling Google Cloud client to convert text to speech")
        audio = gcloud_tts_client.tts(body_data['text'], locale)
        logger.debug("Audio file retrieved from client")
        
        # Ok, this took a while to figure out... GCloud returns the audio in a base64 encoded string,
        # but the python library auto decodes it for us and we get the audio bytes as a result.
        # In order to send it again we have to encode the bytes as base64 and decode it
        # so it can be sent in json (no bytes allowed there)
        # A user can then use it with base64.b64decode(audio_str)
        logger.debug("Encoding audio file to base64 and decoding to string...")
        audio_str = base64.b64encode(audio).decode()
        statistics_client.call_method('registerTextToSpeechCreatedEvent', user_id)

        return jsonify({
            'locale': locale,
            'result': audio_str
        }), 201  # Created
    except TooManyRequestsError as e:
        # Either we have sent too many requests to gcloud...
        # ...or we run out of money! :(
        logger.error(f"There was an error calling google cloud: {e}")
        return jsonify({
            'response': 'Too many requests'
        }), 429
    except Exception as e:
        # Internal server error
        logger.error(f"An error was raised: {e}")
        return jsonify({
            'response': 'Internal server error'
        }), 500
