package com.tech.challenge.openclose.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MessagePublisher {

    void publish(String topic, String msg);
    void publishObject(String topic,Object msg) throws JsonProcessingException;
}
