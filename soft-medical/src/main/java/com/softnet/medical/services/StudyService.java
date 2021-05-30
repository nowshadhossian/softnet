package com.softnet.medical.services;

import com.softnet.medical.dto.StudyPatientDto;
import com.softnet.medical.dto.StudyPatientMapper;
import com.softnet.medical.exception.NotFoundException;
import com.softnet.medical.model.Patient;
import com.softnet.medical.model.Study;
import com.softnet.medical.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudyService {

    private final StudyRepository studyRepository;
    private final StudyPatientMapper mapper;
    private final PatientService patientService;

    public Optional<Study> findById(Long id) {
        return studyRepository.findById(id);
    }

    public Optional<StudyPatientDto> findStudyByStudyId(Long studyId) {
        Study study = studyRepository.findById(studyId).orElseThrow(NotFoundException::new);
        Patient patient = patientService.findById(study.getPatient().getPatientCode()).orElseThrow(NotFoundException::new);
        return Optional.of(mapper.from(study, patient));
    }

    public Page<StudyPatientDto> findAll(Pageable pageable) {
        return studyRepository.findByOrderByCreateDateDesc(pageable)
                .map(study -> mapper.from(study, study.getPatient()));
    }

    public Study saveStudy(StudyPatientDto dto) {
        Study study = mapper.to(dto);
        Patient saved = patientService.save(study.getPatient());
        study.setPatient(saved);
        return studyRepository.save(study);
    }

    public Study save(Study study) {
        return studyRepository.save(study);
    }

    public Study update(StudyPatientDto dto) {
        Study existing = studyRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());

        patientService.findById(dto.getPatientCode())
                .ifPresent(patient -> {
                    patient.setDateOfBirth(dto.getDateOfBirth());
                    patient.setFirstName(dto.getFirstName());
                    patient.setLastName(dto.getLastName());
                    patientService.save(patient);
                });
        return studyRepository.save(existing);
    }

    public void deleteById(Long id) {
        Study study = findById(id).orElseThrow(NotFoundException::new);
        studyRepository.deleteById(study.getId());
    }
}
