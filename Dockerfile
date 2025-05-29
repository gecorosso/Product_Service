FROM openjdk:17-jdk-slim

# Installa curl per health check
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copia il file jar
COPY target/Product_Service-0.0.1-SNAPSHOT.jar app.jar

# Esponi la porta
EXPOSE 8080

# Aggiungi variabili d'ambiente per JVM
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Health check interno
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Comando per avviare l'applicazione
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]