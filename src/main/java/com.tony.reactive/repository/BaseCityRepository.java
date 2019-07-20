package com.tony.reactive.repository;

import com.tony.reactive.domain.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tony
 * @describe BaseCityRepository
 * @date 2019/06/30
 */
@Repository
public class BaseCityRepository {
    private ConcurrentHashMap<Long, City> respository = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(City city) {
        Long id = idGenerator.incrementAndGet();
        city.setId(id);
        respository.put(id, city);
        return id;
    }

    public Collection<City> findAll() {
        return respository.values();
    }

    public City findCityById(Long id) {
        return respository.get(id);
    }

    public Long updateCity(City city) {
        long id = city.getId();
        respository.put(id, city);
        return id;
    }

    public Long deleteCity(Long id) {
        respository.remove(id);
        return id;
    }

}
