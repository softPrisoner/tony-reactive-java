package com.tony.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tony
 * @describe MyTestController
 * @date 2019/07/05
 */
@RestController
@RequestMapping("v1/param/test")
public class MyTestController {

    @GetMapping(params = "str=521")
    public String fooTest521() {
        return "521";
    }

    @GetMapping(params = "str=1314")
    public String fooTest1314() {
        return "1314";
    }
}
