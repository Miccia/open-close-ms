package com.tech.challenge.frontend.service.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConfiguration kafkaConfiguration;

    @Bean
    public ConsumerFactory<String, String> consumerFactory(KafkaProperties properties) {

        properties.getConsumer().setBootstrapServers(Arrays.asList(kafkaConfiguration.getBrokers().split(",")));
        properties.getConsumer().setGroupId(kafkaConfiguration.getGroupId());
        properties.getConsumer().setClientId(kafkaConfiguration.getConsumerId());
        properties.getConsumer().setIsolationLevel(KafkaProperties.IsolationLevel.READ_COMMITTED);
        properties.getConsumer().setValueDeserializer(StringDeserializer.class);
        properties.getConsumer().setKeyDeserializer(StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

}
