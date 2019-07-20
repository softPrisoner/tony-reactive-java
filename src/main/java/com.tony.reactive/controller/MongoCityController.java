package com.tony.reactive.controller;

import com.tony.reactive.repository.CityRepository;
import com.tony.reactive.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tony
 * @describe CityController
 * @date 2019/06/30
 */
@Controller
@RequestMapping(value = "/v2/city")
public class MongoCityController {
    private static final String CITY_FROM_PATH_NAME = "cityList";
    private static final String CITY_PATH_NAME = "city";
    @Autowired
    CityRepository cityRepository;

    @GetMapping("/hello")
    public Mono<String> hello(final Model model) {
        model.addAttribute("name", "张三");
        model.addAttribute("city", "北京");
        String path = "hello";
        return Mono.create(cityMonoSink -> cityMonoSink.success(path));
    }

    @GetMapping("/page/list")
    public String listPage(final Model model) {
        final Flux<City> cityFluxList = cityRepository.findAll();
        model.addAttribute("cityList", cityFluxList);
        return CITY_FROM_PATH_NAME;
    }

    @GetMapping("/getByName")
    public String getByCityName(final Model model, @RequestParam("cityName") String cityName) {
        final Mono<City> city = cityRepository.findByCityName(cityName);
        model.addAttribute("city", city);
        return CITY_PATH_NAME;
    }
}
