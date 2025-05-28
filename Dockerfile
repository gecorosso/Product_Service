# STAGE 1: build del progetto con Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# STAGE 2: immagine runtime leggera
FROM openjdk:17-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY --from=build /app/target/Product_Service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8094
ENTRYPOINT ["java", "-jar", "app.jar"]


