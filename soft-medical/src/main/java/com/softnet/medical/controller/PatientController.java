package com.softnet.medical.controller;

import com.softnet.medical.exception.NotFoundException;
import com.softnet.medical.model.Patient;
import com.softnet.medical.services.PatientService;
import com.softnet.medical.validator.PatientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    private final PatientValidator patientValidator;

    @GetMapping("/{patientCode}")
    public ResponseEntity<Patient> getPatientByCode(@PathVariable String patientCode) {
        return patientService.findById(patientCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody Patient form, BindingResult bindingResult) {
        patientValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Patient saved = patientService.insert(Patient.builder()
                .dateOfBirth(form.getDateOfBirth())
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .build());
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{patientCode}")
    public ResponseEntity<?> update(@PathVariable String patientCode, @RequestBody Patient form) {
        return patientService.findById(patientCode)
                .map(device -> ResponseEntity.ok(patientService.update(form)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{patientCode}")
    public ResponseEntity<?> delete(@PathVariable String patientCode) {
        Patient patient = patientService.findById(patientCode).orElse(null);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        patientService.deleteById(patientCode);
        return ResponseEntity.ok().build();
    }
}
