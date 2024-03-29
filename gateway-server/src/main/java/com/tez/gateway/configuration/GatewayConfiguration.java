package com.tez.gateway.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tez.gateway.filter.AuthFilter;
import com.tez.gateway.filter.AuthFilterConfig;

@Configuration
@RequiredArgsConstructor
public class GatewayConfiguration {


    private final AuthFilter authenticationFilter;

    @Bean
    public RouteLocator gateWayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service",
                        r -> r.path("/v1/api/users/**")
                                .filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
                                .uri("lb://user-service"))
                .route("todo-service",
                        r -> r.path("/v1/api/todo/**")
                                .filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
                                .uri("lb://todo-service"))
                .route("project-service",
                        r -> r.path("/v1/api/project/**")
                                .filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
                                .uri("lb://project-service"))
                .route("calendar-service",
                        r -> r.path("/v1/api/calendar/**")
                                .filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
                                .uri("lb://calendar-service"))
                .build();

    }
}
