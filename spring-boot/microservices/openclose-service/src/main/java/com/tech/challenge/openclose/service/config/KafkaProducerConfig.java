package com.tech.challenge.openclose.service.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Arrays;

@Configuration
public class KafkaProducerConfig {

    @Autowired
    KafkaConfiguration kafkaConfiguration;


    @Bean
    public ProducerFactory<String, String> producerFactory() {

        KafkaProperties properties = new KafkaProperties();
        properties.getProducer().setBootstrapServers(Arrays.asList(kafkaConfiguration.getBrokers().split(",")));
        properties.getProducer().setKeySerializer(StringSerializer.class);
        properties.getProducer().setValueSerializer(StringSerializer.class);
        properties.getProducer().setClientId(kafkaConfiguration.getProducerId());
        return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic adviceTopic(){
        return new NewTopic(kafkaConfiguration.getProducerTopic(),kafkaConfiguration.getPartitionNumber(),kafkaConfiguration.getReplicaFactor());
    }
}
