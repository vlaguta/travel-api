package com.travel.controller;

import com.travel.entity.TravelEntity;
import com.travel.repository.TravelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.travel.utils.FileReadUtils.readJsonFileContentAsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TravelControllerIntegrationTest {

    @Autowired
    TravelRepository travelRepository;
    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void deleteAll() {
        travelRepository.deleteAll();
    }

    @Test
    public void createTravelSuccessfullyTest() throws Exception {

        String content = readJsonFileContentAsString("/create-travel-check-request-success.json");
        mockMvc.perform(post("/travels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());

        assertEquals(7, travelRepository.findAll().size());
        TravelEntity travel = travelRepository.findById(1L).get();
        assertEquals("user-1", travel.getUsername());
        assertEquals("Belarus", travel.getCountry());
        assertEquals("some description", travel.getDescription());
        assertEquals(LocalDate.of(2003, 4, 12), travel.getTravelDate());
    }

    @Test
    public void getAllTravelsSuccessfullyTest() throws Exception {

        mockMvc.perform(get("/travels?lat=55.75396&lon=37.620393")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].username").value("user-1"))
                .andExpect(jsonPath("$[0].country").value("Belarus"))
                .andExpect(jsonPath("$[0].description").value("some description"));
    }

    @Test
    public void getTravelSuccessfullyTest() throws Exception {

        this.mockMvc.perform(get("/travels/1?lat=55.75396&lon=37.620393")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username").value("user-1"))
                .andExpect(jsonPath("$.country").value("Belarus"))
                .andExpect(jsonPath("$.description").value("some description"));
    }
}
