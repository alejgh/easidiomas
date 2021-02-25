FROM openjdk:8-jdk-alpine
ADD target/chats.jar chats.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","chats.jar"]