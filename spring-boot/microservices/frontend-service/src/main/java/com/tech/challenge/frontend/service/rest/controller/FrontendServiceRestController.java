package com.tech.challenge.frontend.service.rest.controller;


import com.tech.challenge.frontend.service.service.RestaurantEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("restaurants")
public class FrontendServiceRestController {

    private static final Logger log = LoggerFactory.getLogger(FrontendServiceRestController.class);

    @Autowired
    RestaurantEntityService restaurantService;

    enum RestaurantStatus{ALL,OPEN,CLOSED}

    @GetMapping("list/{status}")
    public ResponseEntity<Object> getAllRestaurants(@PathVariable(name="status",required = false) Optional<RestaurantStatus> status){
       log.info("status : {} ctrl:{}",status.orElse(null),RestaurantStatus.ALL.compareTo(status.get()));

        return ResponseEntity.ok(restaurantService.findAll());
    }


}
