package com.softnet.medical.config;

import com.softnet.medical.model.Patient;
import com.softnet.medical.model.Study;
import com.softnet.medical.services.PatientService;
import com.softnet.medical.services.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InitDataLoader  implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    private final StudyService studyService;
    private final PatientService patientService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup){
            return;
        }

        Patient patient = patientService.insert(Patient.builder()
                .firstName(UUID.randomUUID().toString().replace("-", ""))
                .lastName("Lame")
                .dateOfBirth(LocalDate.now())
                .build());

        Study study = Study.builder()
                .description("small desc")
                .name(UUID.randomUUID().toString().replace("-", ""))
                .patient(patient)
                .build();
        studyService.save(study);

        alreadySetup = true;
    }
}
