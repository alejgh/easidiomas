FROM gradle:jdk8
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
ENTRYPOINT ["gradle", "runServer"]