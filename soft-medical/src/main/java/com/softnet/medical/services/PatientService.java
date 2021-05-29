package com.softnet.medical.services;

import com.softnet.medical.exception.NotFoundException;
import com.softnet.medical.model.Patient;
import com.softnet.medical.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public Optional<Patient> findById(String patientCode) {
        return patientRepository.findById(patientCode);
    }

    public void deleteById(String patientCode) {
        patientRepository.deleteById(patientCode);
    }

    public Patient update(Patient patient) {
        Patient existing = patientRepository.findById(patient.getPatientCode()).orElseThrow(NotFoundException::new);
        return save(existing);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }


    public Patient insert(Patient patient) {
        return patientRepository.save(patient);
    }
}
