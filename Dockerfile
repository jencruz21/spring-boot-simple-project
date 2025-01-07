# windows setup openjdk:17-alpine
FROM openjdk:17-slim-buster

WORKDIR app

ENV DATABASE_NAME=tasks
ENV DATABASE_HOST=localhost
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=admin
ENV DATABASE_PORT=3306
ENV USER_USERNAME=root
ENV USER_PASSWORD=12345

COPY target/TaskApplication-1.0.0.jar .

CMD ["java", "-jar", "./TaskApplication-1.0.0.jar"]