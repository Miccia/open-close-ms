package com.tech.challenge.openclose.service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("be.kafka.producer")
public class KafkaConfiguration {

    private String brokers;
    private String producerId;
    private String producerTopic;
    private Integer partitionNumber;
    private Short replicaFactor;
}
