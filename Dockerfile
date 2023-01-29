FROM maven:3.8.7-openjdk-18 AS build

WORKDIR /app

COPY src ./src

COPY pom.xml ./

RUN mvn -f ./pom.xml clean package

FROM openjdk:18.0.2-jdk-slim-buster

COPY --from=build /app/target/one-hundred-doors-practice-1.0-SNAPSHOT.jar /usr/local/lib/one-hundred-doors-practice.jar

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "/usr/local/lib/one-hundred-doors-practice.jar"]