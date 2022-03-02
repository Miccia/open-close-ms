package com.tech.challenge.frontend.service.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.frontend.service.model.dto.RestaurantDTO;
import com.tech.challenge.frontend.service.service.RestaurantEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OpenCloseKafkaListener {

    private Logger log = LoggerFactory.getLogger(OpenCloseKafkaListener.class);

    @Autowired
    ObjectMapper objMapper;

    @Autowired
    RestaurantEntityService restaurantService;

    @KafkaListener(topics="${fe.kafka.consumer.consumerTopic}")
    void listen(String data){
        log.info("incoming data:{}",data);
        try{
            RestaurantDTO incoming = objMapper.readValue(data,RestaurantDTO.class);
            restaurantService.updateRestaurantStatus(incoming.getId(), incoming.getIsOpen());
        }catch(Exception e){
            log.error("{} while serializing data : {}",e.getClass(),e.getMessage());
        }
    }


}
