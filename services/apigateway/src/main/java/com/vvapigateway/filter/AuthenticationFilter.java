package com.vvapigateway.filter;

import com.vvapigateway.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey("Authorization")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String authorizationHeader = exchange.getRequest().getHeaders().get("Authorization").get(0);
            if (!authorizationHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authorizationHeader.substring(7);
            try {
                if (!jwtService.isTokenValid(token, jwtService.extractUsername(token))) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
                //extracting extra claims
                Map user = jwtService.extractClaim(token, claims -> claims.get("user", Map.class));
                System.out.println(user);
                exchange.getRequest().mutate().headers(httpHeaders -> {
                    httpHeaders.add("userId", user.get("userId").toString());
                    httpHeaders.add("role", user.get("role").toString());
                    httpHeaders.add("email",user.get("email").toString());
                    httpHeaders.add("name",user.get("name").toString());
                });
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    public static class Config {
    }


}
