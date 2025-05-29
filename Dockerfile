FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia il file jar (sostituisci con il nome del tuo jar)
COPY target/nome-del-tuo-app.jar app.jar

# Esponi la porta
EXPOSE 8080

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]


