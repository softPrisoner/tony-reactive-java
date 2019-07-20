package com.tony.reactive.repository;

import com.tony.reactive.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe CityRepository
 * @date 2019/06/30
 */
public interface CityRepository extends ReactiveMongoRepository<City, Long> {
    Mono<City> findByCityName(String cityName);
}
