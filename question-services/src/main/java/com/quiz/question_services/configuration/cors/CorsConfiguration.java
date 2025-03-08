package com.quiz.question_services.configuration.cors;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration {

    public WebMvcConfigurer corsConfigurer() {
        //TODO enabling all, to be removed later
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "HEAD", "OPTIONS")
                        .allowedOrigins("*")
                        .exposedHeaders("Authorization");
            }
        };
    }
}
