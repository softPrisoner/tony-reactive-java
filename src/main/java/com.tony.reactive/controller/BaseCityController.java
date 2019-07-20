package com.tony.reactive.controller;

import com.tony.reactive.domain.City;
import com.tony.reactive.handler.BaseCityHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * @author tony
 * @describe BaseCityController
 * @date 2019/06/30
 */
@RestController
@RequestMapping("/v1/city")
public class BaseCityController {
    @Autowired
    private BaseCityHandler cityHandler;

    @GetMapping("/{id}")
    public Mono<City> findCityById(@PathVariable Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping()
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }

    @PostMapping()
    public Mono<City> saveCity(@RequestBody City city) {
        return cityHandler.save(city);
    }

    @PutMapping()
    public Mono<City> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }
}
