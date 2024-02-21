package com.epam.springcore.actuator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class LoggerHealthIndicator implements HealthIndicator {

    private static final Logger logger = LogManager.getLogger(LoggerHealthIndicator.class);

    @Override
    public Health health() {
        try {
            logger.info("Health check - logger is working");
            return Health.up().build();
        } catch (Exception e) {
            return Health.down().withDetail("Logger", "Not working: " + e.getMessage()).build();
        }
    }
}
