package com.stackroute.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p -> p
                        .path("/login/**") //Authentication service
                        .uri("http://localhost:9091/"))
                .route(p -> p
                        .path("/api/v2/**") //Location service
                        .uri("http://localhost:9092/"))
                .route(p -> p
                        .path("/api/v3/**") //Payment service
                        .uri("http://localhost:9093/"))
                .route(p -> p
                        .path("/api/v4/**") //Review service
                        .uri("http://localhost:9094/"))
                .route(p -> p
                        .path("/api/v5/**") //Ticket Booking service
                        .uri("http://localhost:9095/"))
                .route(p -> p
                        .path("/api/v6/**") //User service
                        .uri("http://localhost:9096/"))
                .route(p -> p
                        .path("/api/v7/**") //Notification service
                        .uri("http://localhost:9112/"))
                .route(p -> p
                        .path("/api/v8/**") //customer care service
                        .uri("http://localhost:9097/"))
                .route(p -> p
                        .path("/**")
                        .uri("http://localhost:9020/"))
                .build();
    }
}
