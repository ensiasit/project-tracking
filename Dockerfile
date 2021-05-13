FROM maven:3.8.1-openjdk-11-slim as BUILD_STAGE

WORKDIR /api

COPY ./pom.xml ./
COPY ./src/ ./src/

RUN ["mvn", "clean", "install"]

FROM openjdk:11.0.6-jre-slim

WORKDIR /api

COPY --from=BUILD_STAGE /api/target/*.jar api.jar
COPY ./entrypoint.sh ./entrypoint.sh
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.8.0/wait ./wait

RUN chmod +x ./wait
RUN chmod +x ./entrypoint.sh

CMD ./wait && ./entrypoint.sh