package com.epam.springcore.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestTimer {

    private final Timer requestTimer;

    @Autowired
    public RequestTimer(MeterRegistry meterRegistry) {
        this.requestTimer = Timer.builder("http_requests_duration_seconds")
                .description("Duration of HTTP requests")
                .register(meterRegistry);
    }

    public Timer.Sample startTimer() {
        return Timer.start();
    }

    public void stopTimer(Timer.Sample sample) {
        sample.stop(requestTimer);
    }
}