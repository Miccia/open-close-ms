FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/frontend-service-0.0.1-SNAPSHOT.jar consumer-fe-service.jar
ENTRYPOINT ["java","-jar","/consumer-fe-service.jar"]