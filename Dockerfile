FROM openjdk:17-alpine

WORKDIR app

COPY target/TaskApplication-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "./TaskApplication-0.0.1-SNAPSHOT.jar"]