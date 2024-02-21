package com.epam.springcore.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestCounter {

    private final Counter httpRequestsCounter;

    @Autowired
    public RequestCounter(MeterRegistry meterRegistry) {
        this.httpRequestsCounter = Counter.builder("http_requests_total")
                .description("Total number of HTTP requests")
                .register(meterRegistry);
    }

    public void incrementHttpRequestCounter() {
        httpRequestsCounter.increment();
    }
}
