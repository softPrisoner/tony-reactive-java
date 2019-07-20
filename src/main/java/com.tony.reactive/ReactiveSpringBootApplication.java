package com.tony.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author tony
 * @describe ReactiveSpringBootApplication
 */
@SpringBootApplication
@EnableWebFlux
public class ReactiveSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveSpringBootApplication.class, args);
    }

}
