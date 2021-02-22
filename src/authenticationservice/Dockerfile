FROM gradle:jdk8
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
EXPOSE 5000
ENTRYPOINT ["gradle", "runServer", "-d"]