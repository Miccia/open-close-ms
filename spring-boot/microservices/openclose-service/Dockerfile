FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/openclose-service-0.0.1-SNAPSHOT.jar producer-be-service.jar
ENTRYPOINT ["java","-jar","/producer-be-service.jar"]