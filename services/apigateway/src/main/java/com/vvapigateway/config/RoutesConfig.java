package com.vvapigateway.config;

import com.vvapigateway.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class RoutesConfig {
    private final AuthenticationFilter authenticationFilter;
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/subject/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://SUBJECT-SERVICE"))
                .route(p -> p
                        .path("/auth/**")
                        .uri("lb://USER-SERVICE"))
                .route(p -> p
                        .path("/quiz/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://QUIZ-SERVICE"))
                .route(p -> p
                        .path("/question/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://QUESTION-SERVICE"))
                .build();
    }
}
