package com.epam.springcore.config;

import com.epam.springcore.interceptor.TransactionLoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TransactionLoggingInterceptor())
                .addPathPatterns("/**"); // Add path patterns for which you want to apply the interceptor
    }
}
