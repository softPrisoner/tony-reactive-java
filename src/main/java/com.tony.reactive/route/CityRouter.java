package com.tony.reactive.route;

import com.tony.reactive.handler.CityHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author tony
 * @describe CityRouter
 * @date 2019/06/30
 */
@Configuration
public class CityRouter {
    @Bean
    public RouterFunction<ServerResponse> routeCity(CityHandler cityHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), cityHandler::helloCity);
    }
}
