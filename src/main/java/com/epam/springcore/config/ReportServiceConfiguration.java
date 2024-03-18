package com.epam.springcore.config;

import feign.RequestInterceptor;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ReportServiceConfiguration {

    @Bean
    public RequestInterceptor mdcRequestInterceptor() {
        return requestTemplate -> {
            String transactionId = MDC.get("transactionId");
            requestTemplate.header("transactionId", transactionId);
            //requestTemplate.header(HttpHeaders.AUTHORIZATION);
        };
    }
}
