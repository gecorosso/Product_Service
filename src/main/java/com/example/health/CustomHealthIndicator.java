package com.example.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        try {
            // Qui puoi aggiungere controlli specifici
            // Ad esempio, verificare la connessione al database
            
            return Health.up()
                    .withDetail("app", "Product Service")
                    .withDetail("status", "Running")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("app", "Product Service")
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}