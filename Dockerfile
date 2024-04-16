# FROM jenkins/jenkins
# USER root
# RUN apt-get update && apt-get install -y docker.io
# USER jenkins
FROM openjdk:17

WORKDIR /app
COPY target/YouAndMe-0.0.1-SNAPSHOT.jar app.jar 
ENTRYPOINT [ "java", "-jar" , "app.jar" ]