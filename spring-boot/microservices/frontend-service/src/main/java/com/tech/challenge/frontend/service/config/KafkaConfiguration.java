package com.tech.challenge.frontend.service.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("fe.kafka.consumer")
public class KafkaConfiguration {
    private String brokers;
    private String consumerId;
    private String groupId;
    private String consumerTopic;
    private String isolationLevel;
}
