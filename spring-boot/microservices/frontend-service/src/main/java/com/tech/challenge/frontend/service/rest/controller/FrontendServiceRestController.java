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

@RestController
@RequestMapping("restaurants")
public class FrontendServiceRestController {

    private static final Logger log = LoggerFactory.getLogger(FrontendServiceRestController.class);

    @Autowired
    RestaurantEntityService restaurantService;

    enum RestaurantStatus{ALL,OPEN,CLOSED}

    @GetMapping("list/{status}")
    public ResponseEntity<Object> getAllRestaurants(@PathVariable(name="status",required = true) RestaurantStatus status){
        if(status.equals(RestaurantStatus.ALL))
            return ResponseEntity.ok(restaurantService.findAll());
        else return ResponseEntity.ok(restaurantService.findByStatus(status.equals(RestaurantStatus.OPEN)?true:false));
    }

}
