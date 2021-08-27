package com.dnd.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "http://localhost:8081/Personnages";

    @GetMapping("/characterListApi")
    public List<Object> getCharacterApi() {
        Object[] character = restTemplate.getForObject(url, Object[].class);
        return Arrays.asList(character);
    }
}
