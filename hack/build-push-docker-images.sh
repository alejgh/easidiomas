
docker build ../src/apigateway -t registry.easidiomas.wcr.es:7777/apigateway_service;
docker push registry.easidiomas.wcr.es:7777/apigateway_service;

docker build ../src/authenticationservice -t registry.easidiomas.wcr.es:7777/authentication_service;
docker push registry.easidiomas.wcr.es:7777/authentication_service;

docker build ../src/chatsservice -t registry.easidiomas.wcr.es:7777/chats_service;
docker push registry.easidiomas.wcr.es:7777/chats_service;

docker build ../src/imagesservice -t registry.easidiomas.wcr.es:7777/images_service;
docker push registry.easidiomas.wcr.es:7777/images_service;

docker build ../src/languageidentificationservice -t registry.easidiomas.wcr.es:7777/languageidentification_service;
docker push registry.easidiomas.wcr.es:7777/languageidentification_service;

docker build ../src/loadgenerator -t registry.easidiomas.wcr.es:7777/load_generator;
docker push registry.easidiomas.wcr.es:7777/load_generator;

docker build ../src/offensivetextdetectionservice -t registry.easidiomas.wcr.es:7777/offensivetextdetection_service;
docker push registry.easidiomas.wcr.es:7777/offensivetextdetection_service;

docker build ../src/postsservice -t registry.easidiomas.wcr.es:7777/posts_service;
docker push registry.easidiomas.wcr.es:7777/posts_service;

docker build ../src/statisticsservice -t registry.easidiomas.wcr.es:7777/statistics_service;
docker push registry.easidiomas.wcr.es:7777/statistics_service;

docker build ../src/texttospeechservice -t registry.easidiomas.wcr.es:7777/texttospeech_service;
docker push registry.easidiomas.wcr.es:7777/texttospeech_service;

docker build ../src/topicmodelingservice -t registry.easidiomas.wcr.es:7777/topicmodeling_service;
docker push registry.easidiomas.wcr.es:7777/topicmodeling_service;

docker build ../src/translationsservice -t registry.easidiomas.wcr.es:7777/translations_service;
docker push registry.easidiomas.wcr.es:7777/translations_service;

docker build ../src/usersservice -t registry.easidiomas.wcr.es:7777/users_service;
docker push registry.easidiomas.wcr.es:7777/users_service;

docker build ../src/webclient -t registry.easidiomas.wcr.es:7777/web_client;
docker push registry.easidiomas.wcr.es:7777/web_client;