""" Entry point of the app.
"""

from src import create_app

import logging

logging.basicConfig(level=logging.INFO)

app = create_app()

if __name__ == '__main__':
    if app.config['ENV'] == 'production':
        from waitress import serve
        serve(app, host='0.0.0.0', port=app.config['DEPLOY_PORT'])
    else:
        app.run(host='0.0.0.0')