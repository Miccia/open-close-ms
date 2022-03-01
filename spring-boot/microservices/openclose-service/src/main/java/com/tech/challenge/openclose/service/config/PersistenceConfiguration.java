package com.tech.challenge.openclose.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tech.challenge.openclose.service.model.repo")
@EntityScan(basePackages = "com.tech.challenge.openclose.service.model.entity")
public class PersistenceConfiguration {
}
