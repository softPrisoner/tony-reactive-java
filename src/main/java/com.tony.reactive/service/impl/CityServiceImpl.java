package com.tony.reactive.service.impl;

import com.tony.reactive.repository.CityRepository;
import com.tony.reactive.domain.City;
import com.tony.reactive.service.CityService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe CityService
 * @date 2019/06/30
 */
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Flux<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Mono<City> insertByCity(City city) {
        return cityRepository.insert(city);
    }

    @Override
    public Mono<City> update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return cityRepository.deleteById(id);
    }

    @Override
    public Mono<City> findById(Long id) {
        return cityRepository.findById(id);
    }
}
