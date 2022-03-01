package com.tech.challenge.openclose.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("producer.kafka.topics")
public class TopicConfiguration {

    private String restaurantsTopic;
}
