FROM python:3.8.7-slim

EXPOSE 5000

RUN mkdir app
COPY . app
WORKDIR app
RUN pip install -r requirements.txt
ENV FLASK_CONFIG=production
ENV GOOGLE_APPLICATION_CREDENTIALS=keys.json
ENTRYPOINT ["python", "wsgi.py"]