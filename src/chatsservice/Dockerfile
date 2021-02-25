FROM maven:3-jdk-8 AS build
COPY . /home/src
WORKDIR /home/src
RUN mvn clean package

FROM openjdk:8-jre-slim
EXPOSE 5000
RUN mkdir /app
COPY --from=build /home/src/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]