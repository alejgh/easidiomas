import logging

from flask import current_app as app
from google.cloud import texttospeech


logger = logging.getLogger(app.config['SERVICE_KEY'])
logger.setLevel(logging.DEBUG)


class GCloudTTSClient():
    def __init__(self):
        logger.debug("Initializing google cloud client")
        self.client = texttospeech.TextToSpeechClient()
        self.audio_config = texttospeech.AudioConfig(
            audio_encoding=texttospeech.AudioEncoding.MP3
        )
    
    def tts(self, text, locale):
        logger.debug(f"Calling GCloud tts with params[text='{text}', locale={locale}]")
        synthesis_input = texttospeech.SynthesisInput(text=text)
        voice = texttospeech.VoiceSelectionParams(language_code=locale,
            ssml_gender=texttospeech.SsmlVoiceGender.NEUTRAL)
        response = self.client.synthesize_speech(input=synthesis_input, voice=voice,
            audio_config=self.audio_config)
        return response.audio_content