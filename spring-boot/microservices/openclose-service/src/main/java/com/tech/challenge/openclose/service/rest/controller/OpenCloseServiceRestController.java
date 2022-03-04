package com.tech.challenge.openclose.service.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.challenge.openclose.service.config.KafkaConfiguration;
import com.tech.challenge.openclose.service.model.dto.RestaurantDTO;
import com.tech.challenge.openclose.service.service.MessagePublisher;
import com.tech.challenge.openclose.service.service.RestaurantEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("restaurants")
public class OpenCloseServiceRestController {

    private static final Logger log = LoggerFactory.getLogger(OpenCloseServiceRestController.class);

    @Autowired
    MessagePublisher kProducer;
    @Autowired
    RestaurantEntityService entityService;
    @Autowired
    KafkaConfiguration kafkaConfiguration;
    
    @PostMapping(value="update/{id}")
    public ResponseEntity<String> updateRestaurantStatus(@PathVariable("id") String id,
                                                         @RequestParam Boolean isOpen) throws JsonProcessingException {

        entityService.updateRestaurantStatus(id,isOpen);
        RestaurantDTO restaurant = new RestaurantDTO();
        restaurant.setId(id);
        restaurant.setIsOpen(isOpen);
        kProducer.publishObject(kafkaConfiguration.getProducerTopic(),restaurant);

        return ResponseEntity.accepted().body(id+" updated");
    }

    @GetMapping(value="status/{id}")
    public ResponseEntity<String> getRestaurantStatus(@PathVariable("id") String id) throws JsonProcessingException {
        RestaurantDTO restaurant;
        try {
           restaurant = entityService.getRestaurantById(id);
        }catch(EntityNotFoundException notFound){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(String.format("Restaurant %s is %s",restaurant.getName(),restaurant.getIsOpen()?"open":"closed"));
    }

}
