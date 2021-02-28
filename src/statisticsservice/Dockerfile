FROM maven:3-jdk-8 AS build
COPY . /home/src
WORKDIR /home/src
RUN mvn clean package

FROM tomcat:9.0.43-jdk8
EXPOSE 5000

RUN mkdir /priv
WORKDIR /usr/local/tomcat/

# change default port to 5000
RUN sed -i 's/port="8080"/port="5000"/' ${CATALINA_HOME}/conf/server.xml

# copy war and gcloud credentials
COPY --from=build /home/src/target/*.war ./webapps/ROOT.war

RUN cd bin
CMD ["catalina.sh", "run"]