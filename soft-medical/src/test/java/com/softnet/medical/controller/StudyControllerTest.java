package com.softnet.medical.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softnet.medical.model.Patient;
import com.softnet.medical.model.Study;
import com.softnet.medical.services.PatientService;
import com.softnet.medical.services.StudyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class StudyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    StudyService studyService;
    @Autowired
    PatientService patientService;


    @Test
    void studyList_shouldSucceed() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/study/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isArray());
    }

    @Transactional
    @Test
    void studyCreate_shouldSucceed() throws Exception {
        Patient patient = patientService.save(Patient.builder()
                .lastName("Kamal")
                .firstName("John")
                .dateOfBirth(LocalDate.of(2000, 12, 3))
                .build());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/study")
                .content(objectMapper.writeValueAsString(Study.builder()
                        .patient(patient)
                        .name("Human Hand")
                        .description("Study of elbow")
                        .build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }



}
