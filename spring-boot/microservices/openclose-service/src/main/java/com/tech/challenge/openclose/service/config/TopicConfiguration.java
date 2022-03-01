package com.tech.challenge.openclose.service.config;

import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("producer.kafka.topics")
public class TopicConfiguration {

    private String restaurantsTopic;
    private Integer partitionNumber;
    private Short replicaFactor;

    @Bean
    public NewTopic adviceTopic(){
        return new NewTopic(this.getRestaurantsTopic(),this.getPartitionNumber(),this.getReplicaFactor());
    }

}
