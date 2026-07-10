FROM maven:3.9.9-eclipse-temurin-21 AS build

COPY src /app/src
COPY pom.xml /app/

WORKDIR /app

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre

COPY --from=build /app/target/com-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]