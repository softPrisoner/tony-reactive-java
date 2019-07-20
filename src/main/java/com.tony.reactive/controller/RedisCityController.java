package com.tony.reactive.controller;

import com.tony.reactive.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author tony
 * @describe BaseCityController
 * @date 2019/06/30
 */
@RestController
@RequestMapping("/v3/city/redis")
public class RedisCityController {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String KEY_PRE = "city_";

    @GetMapping("/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        String key = KEY_PRE + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        City city = operations.get(key);
        if (!hasKey) {
            return Mono.create(redisMonoSink -> redisMonoSink.success(null));
        }
        return Mono.create(redisMonoSink -> redisMonoSink.success(city));
    }

    @PostMapping
    public Mono<City> saveCity(@RequestBody City city) {
        String key = KEY_PRE + city.getId();
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        operations.set(key, city, 120, TimeUnit.SECONDS);
        return Mono.create(redisMonoSink -> redisMonoSink.success(city));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        String key = KEY_PRE + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return Mono.create(redisMonoSink -> redisMonoSink.success(id));
    }
}
