package com.tony.reactive.service;

import com.tony.reactive.domain.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe CityService
 * @date 2019/06/30
 */
public interface CityService {
    Flux<City> findAll();

    Mono<City> insertByCity(City city);

    Mono<City> update(City city);

    Mono<Void> delete(Long id);

    Mono<City> findById(Long id);

}
