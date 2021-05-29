package com.softnet.medical.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softnet.medical.model.Patient;
import com.softnet.medical.model.Study;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
@JsonIgnoreProperties(ignoreUnknown = true)
public interface StudyPatientMapper {

    @Mappings({
        @Mapping(source = "patient.firstName", target = "firstName"),
        @Mapping(source = "patient.patientCode", target = "patientCode"),
        @Mapping(source = "patient.lastName", target = "lastName"),
        @Mapping(source = "patient.dateOfBirth", target = "dateOfBirth"),
        @Mapping(target = "fullName", expression = "java(patient.getFirstName() + \" \" + patient.getLastName())"),
        @Mapping(source = "study.id", target = "id"),
        @Mapping(source = "study.name", target = "name"),
        @Mapping(source = "study.createDate", target = "createDate"),
        @Mapping(source = "study.updateDate", target = "updateDate"),
    })
    StudyPatientDto from(Study study, Patient patient);

    @Mappings({
            @Mapping(target = "patient.firstName", source = "firstName"),
            @Mapping(target = "patient.patientCode", source = "patientCode"),
            @Mapping(target = "patient.lastName", source = "lastName"),
            @Mapping(target = "patient.dateOfBirth", source = "dateOfBirth"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "createDate", source = "createDate"),
            @Mapping(target = "updateDate", source = "updateDate"),
    })
    Study to(StudyPatientDto dto);
}
