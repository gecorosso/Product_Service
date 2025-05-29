FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia il file jar (sostituisci con il nome del tuo jar)
COPY target/Product_Service-0.0.1-SNAPSHOT.jar app.jar

# Esponi la porta
EXPOSE 8080

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]


