FROM openjdk:17-alpine

WORKDIR app

COPY target/TaskApplication-1.0.0.jar .

CMD ["java", "-jar", "./TaskApplication-1.0.0.jar"]