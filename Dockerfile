FROM maven:3.6.3-jdk-8-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/

RUN mvn package


FROM openjdk:8-jdk-alpine

COPY --from=MAVEN_BUILD /build/target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080

