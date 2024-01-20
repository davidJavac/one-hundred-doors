FROM maven:3.9.0-amazoncorretto-17 AS build

WORKDIR /app

COPY src ./src

COPY pom.xml ./

RUN mvn -f ./pom.xml clean package

FROM openjdk:17.0.2-jdk-slim-buster

COPY --from=build /app/target/one-hundred-doors-practice-1.0-SNAPSHOT.jar /usr/local/lib/one-hundred-doors-practice.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/usr/local/lib/one-hundred-doors-practice.jar"]