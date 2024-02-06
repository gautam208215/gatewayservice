package com.example.gateway.service.gateway.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;




@Configuration
public class GatewayConfig {

//    @Bean
//    public TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory() {
//        return new TokenRelayGatewayFilterFactory();
//    }

//    @Autowired
//    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service-one", r -> r.path("/first/**")
//                        .filters(f -> f.filter(filterFactory.apply()))
                        .uri("http://service-one.dental.svc.cluster.local"))
                .build();
    }




//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("resource", r -> r.path("/resource")
//                        .filters(f -> f.tokenRelay())
//                        .uri("http://localhost:9000"))
//                .build();
//    }


//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange(exchange -> exchange.matchers(EndpointRequest.toAnyEndpoint()).permitAll()
//                        .anyExchange().authenticated())
//                .oauth2Login(Customizer.withDefaults())
//                .build();
//    }

//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
//        http
//                .authorizeExchange(authorizeRequests ->
//                        authorizeRequests
//                                .anyExchange().authenticated()
//                ).csrf().disable()
//                .oauth2Client(Customizer.withDefaults());
//
//        return http.build();
//    }



    @RestController
    class FallbackController {

        private static final Logger log = LoggerFactory.getLogger(FallbackController.class);

        @GetMapping("/books-fallback")
        Flux<Void> getBooksFallback() {
            log.info("Fallback for book service");
            return Flux.empty();
        }


    }
}