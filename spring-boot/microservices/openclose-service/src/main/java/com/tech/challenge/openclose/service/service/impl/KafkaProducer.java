package com.tech.challenge.openclose.service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.openclose.service.service.MessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer implements MessagePublisher {

 private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
 private final KafkaTemplate<String,String> kTemplate;
@Autowired
    ObjectMapper objMapper;

     public KafkaProducer(KafkaTemplate kTemplate){
         this.kTemplate =kTemplate;
     }

     @Override
     public void publish(String topic,String msg){
         log.info("publishing on topic:[{}], msg:{}",topic,msg);
         kTemplate.send(topic,msg);
     }

    @Override
    public void publishObject(String topic, Object msg) throws JsonProcessingException {
         String jsonMsg = objMapper.writeValueAsString(msg);
        log.info("publishing on topic:[{}], msg:{}",topic,jsonMsg);
        kTemplate.send(topic,jsonMsg);
    }
}
