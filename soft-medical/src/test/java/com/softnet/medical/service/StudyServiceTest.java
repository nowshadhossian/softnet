package com.softnet.medical.service;

import com.softnet.medical.exception.AppException;
import com.softnet.medical.model.Study;
import com.softnet.medical.repository.StudyRepository;
import com.softnet.medical.services.StudyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StudyServiceTest {
    @Autowired
    StudyService studyService;

    @MockBean
    StudyRepository studyRepository;

    @Test
    public void findById_shouldReturn(){
        Mockito.when(studyRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(Study.builder()
                .name("ABCD")
                .build()));
        assertEquals(studyService.findById(55L).get().getName(), "ABCD");
    }


}
