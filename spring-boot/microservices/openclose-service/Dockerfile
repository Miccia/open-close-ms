# lazy "build"
# FROM openjdk:8-jdk-alpine
# EXPOSE 8080
# COPY target/*.jar producer-be-service.jar
# ENTRYPOINT ["java","-jar","/producer-be-service.jar"]
FROM maven:3.5-jdk-8-alpine as builder
COPY . ./
RUN mvn -B  de.qaware.maven:go-offline-maven-plugin:resolve-dependencies -q
RUN mvn clean install -Dmaven.test.skip
RUN mv `ls -1 target/*.jar` target/fatjar.jar
FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY --from=builder /target/fatjar.jar fatjar.jar
ENTRYPOINT ["java","-jar","fatjar.jar"]