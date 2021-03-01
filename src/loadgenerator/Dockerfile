FROM python:3.8.7-slim

RUN mkdir app
COPY . app
WORKDIR app
RUN pip install -r requirements.txt

ENTRYPOINT ["python", "seed_system.py"]