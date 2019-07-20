package com.tony.reactive.controller;

import com.tony.reactive.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe BaseCityController
 * @date 2019/06/30
 */
@RestController
@RequestMapping("/v3/city")
public class ReactiveRedisCityController {
    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @GetMapping("/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = "city_" + id;
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        Mono<City> city = operations.get(key);
        return city;

    }

    @PostMapping("")
    public Mono<City> saveCity(@RequestBody City city) {
        String key = "city_" + city.getId();
        ReactiveValueOperations<String, City> operations = reactiveRedisTemplate.opsForValue();
        return operations.getAndSet(key, city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = "city_" + id;
        return reactiveRedisTemplate.delete(key);
    }
}
