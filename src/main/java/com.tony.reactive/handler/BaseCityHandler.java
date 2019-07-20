package com.tony.reactive.handler;

import com.tony.reactive.repository.CityRepository;
import com.tony.reactive.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe BaseCityHandler
 * @date 2019/06/30
 */
@Component
public class BaseCityHandler {
    private final CityRepository baseCityRepository;

    @Autowired
    public BaseCityHandler(CityRepository baseCityRepository) {
        this.baseCityRepository = baseCityRepository;
    }

    public Mono<City> save(City city) {
        return baseCityRepository.save(city);
    }

    public Mono<City> findCityById(Long id) {
        return baseCityRepository.findById(id);
    }

    public Flux<City> findAllCity() {
        return baseCityRepository.findAll();
    }

    public Mono<City> modifyCity(City city) {
        return baseCityRepository.save(city);
    }

    public Mono<Long> deleteCity(Long id) {
        baseCityRepository.deleteById(id);
        return Mono.create(cityMonoSink -> cityMonoSink.success(id));
    }

    public Mono<City> getByCityName(String cityName) {
        return baseCityRepository.findByCityName(cityName);
    }

}
