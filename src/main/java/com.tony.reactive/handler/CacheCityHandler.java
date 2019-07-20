package com.tony.reactive.handler;

import com.tony.reactive.repository.CityRepository;
import com.tony.reactive.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe BaseCityHandler
 * @date 2019/06/30
 */
@Component
public class CacheCityHandler {
    private final CityRepository baseCityRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    public CacheCityHandler(CityRepository baseCityRepository) {
        this.baseCityRepository = baseCityRepository;
    }

    public Mono<City> save(City city) {
        return baseCityRepository.save(city);
    }

    public Mono<City> findCityById(Long id) {
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = operations.get(key);
            return Mono.create(cacheMonoSink -> cacheMonoSink.success(city));
        }
        Mono<City> cityMono = baseCityRepository.findById(id);
        if (null != cityMono) {
            return cityMono;
        }
        cityMono.subscribe(cityObj -> {
            operations.set(key, cityObj);
        });
        return cityMono;
    }

    public Flux<City> findAllCity() {
        return baseCityRepository.findAll().cache();
    }

    public Mono<City> modifyCity(City city) {
        Mono<City> cityMono = baseCityRepository.save(city);
        String key = "city_" + city.getId();
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }

        return cityMono;
    }

    public Mono<Long> deleteCity(Long id) {
        baseCityRepository.deleteById(id);
        String key = "city_" + id;
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return Mono.create(cityMonoSink -> cityMonoSink.success(id));
    }

    public Mono<City> getByCityName(String cityName) {
        return baseCityRepository.findByCityName(cityName);
    }

}
