package com.tech.challenge.frontend.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.tech.challenge.frontend.service.model.repo")
@EntityScan(basePackages = "com.tech.challenge.frontend.service.model.entity")
public class PersistenceConfiguration {
}
