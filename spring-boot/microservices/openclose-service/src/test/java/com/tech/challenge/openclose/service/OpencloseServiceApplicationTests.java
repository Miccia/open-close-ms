package com.tech.challenge.openclose.service;

import com.tech.challenge.openclose.service.rest.controller.OpenCloseServiceRestController;
import com.tech.challenge.openclose.service.service.impl.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class OpencloseServiceApplicationTests {

	@MockBean
	KafkaProducer kafkaProd;

	@Autowired
	OpenCloseServiceRestController restController;

	@Test
	void contextLoads() {
		assertThat(restController).isNotNull();
	}

}
