package com.tech.challenge.openclose.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.openclose.service.config.KafkaConfiguration;
import com.tech.challenge.openclose.service.config.KafkaProducerConfig;
import com.tech.challenge.openclose.service.model.dto.RestaurantDTO;
import com.tech.challenge.openclose.service.service.impl.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HttpRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    KafkaProducer kafkaProd;

    @Test
    public void testUpdate_OK() throws Exception {

        this.mockMvc.perform(post("/restaurants/update/first?isOpen=false"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString("first updated")));
    }

    @Test
    public void testUpdate_KO() throws Exception {

        this.mockMvc.perform(post("/restaurants/update/first?isOpen=fools"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }
}
