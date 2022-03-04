package com.tech.challenge.frontend.service;

import com.tech.challenge.frontend.service.consumers.OpenCloseKafkaListener;
import com.tech.challenge.frontend.service.rest.controller.FrontendServiceRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class FrontendServiceApplicationTests {

	@MockBean
	OpenCloseKafkaListener listener;
	@Autowired
	FrontendServiceRestController feRestController;

	@Test
	void contextLoads() {
		assertThat(feRestController).isNotNull();
	}

}
