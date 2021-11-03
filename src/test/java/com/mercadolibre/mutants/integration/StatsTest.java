package com.mercadolibre.mutants.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsTest {

    @Autowired
    MockMvc mockMvc;
    @Test
    public void statsExpertoTest() throws Exception {
        mockMvc.perform(get("/api/v1/mutant/stats")).andExpect(status().isOk());
    }

}
