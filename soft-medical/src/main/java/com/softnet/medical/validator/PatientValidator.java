package com.softnet.medical.validator;

import com.softnet.medical.model.Patient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PatientValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Patient.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Patient patient = (Patient) target;

        ValidationUtils.rejectIfEmpty(errors, "firstName", "", "First name cannot be null");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "", "First name cannot be null");
        ValidationUtils.rejectIfEmpty(errors, "dateOfBirth", "", "First name cannot be null");

    }
}
