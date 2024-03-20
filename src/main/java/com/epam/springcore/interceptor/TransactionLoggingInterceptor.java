package com.epam.springcore.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Component
public class TransactionLoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LogManager.getLogger(TransactionLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String transactionId = UUID.randomUUID().toString();
        MDC.put("transactionId", transactionId);
        MDC.put(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        LOGGER.info("[MAIN MICROSERVICE] Transaction Started - [{} {}] - Transaction ID: {}", request.getMethod(), request.getRequestURI(), transactionId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.info("[MAIN MICROSERVICE] Transaction End - [{} {}] - Status: {} - Transaction ID: {}", request.getMethod(), request.getRequestURI(), response.getStatus(), MDC.get("transactionId"));
        MDC.clear();
    }
}
