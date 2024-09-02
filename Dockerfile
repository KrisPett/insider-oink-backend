FROM openjdk:24-slim-bullseye

WORKDIR /

RUN mkdir /app

RUN mkdir /backend-config

COPY server-props/application.properties /backend-config/

COPY ./target/*.jar /app/java-application.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar","/app/java-application.jar", "--spring.config.location=/backend-config/application.properties"]

