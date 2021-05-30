package com.softnet.medical.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softnet.medical.model.Patient;
import com.softnet.medical.model.Study;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyPatientDto {
    private String patientCode;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String fullName;

   //study
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
