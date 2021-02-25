""" Controllers of the text to speech service.
"""

import logging

from flask import current_app as app

@app.route('/api/TextsToSpeechs')
def tts():
    """ Transforms the given text to an audio
    """
    return 'Hello, World!', 200
