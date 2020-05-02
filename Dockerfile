FROM openjdk:8-jdk-alpine

MAINTAINER francisco.seguel@pad.cl

VOLUME /tmp
ARG JAR_FILE
COPY . /usr/src/lpws
WORKDIR /usr/src/lpws

ENTRYPOINT ["java","-jar","target/solution-1.0-SNAPSHOT-dist/solution-1.0-SNAPSHOT.jar"]

EXPOSE 8080