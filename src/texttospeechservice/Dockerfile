FROM python:3.8.7-slim

RUN mkdir app
COPY . app
WORKDIR app
RUN pip install -r requirements.txt
ENV FLASK_APP=app.py
ENV FLASK_CONFIG=production
ENV GOOGLE_CREDENTIALS=keys.json
ENTRYPOINT ["flask", "run", "--host=0.0.0.0", "--port=5001"]