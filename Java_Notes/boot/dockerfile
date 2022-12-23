# It is possible to layer the images you use in order to have more flexibility in how you build/deploy your app
# By setting an alias for the image we can reference its files/directories in later steps
FROM maven:3-adoptopenjdk-11 as builder
COPY ./src src
COPY ./pom.xml pom.xml
RUN mvn package

FROM openjdk:11-jdk-slim
COPY --from=builder target/boot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]