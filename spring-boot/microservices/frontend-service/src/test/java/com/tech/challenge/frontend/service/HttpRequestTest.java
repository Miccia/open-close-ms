package com.tech.challenge.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.challenge.frontend.service.consumers.OpenCloseKafkaListener;
import com.tech.challenge.frontend.service.model.dto.ErrorDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HttpRequestTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    OpenCloseKafkaListener listener;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void testList_OK() throws Exception {

        this.mockMvc.perform(get("/restaurants/list/OPEN"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testList_KO() throws Exception {
        ErrorDTO error = new ErrorDTO();
            error.setError("BAD REQUEST");
            error.setMessage("invalid value:'NOT_VALID' for param:'status'");
        String errorJson = mapper.writeValueAsString(error);
        this.mockMvc.perform(get("/restaurants/list/NOT_VALID"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string(containsString(errorJson)));
    }

    @Test
    public void testFrontEndIsUp() throws Exception{

        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

}
