package com.softnet.medical.controller;

import com.softnet.medical.dto.StudyPatientDto;
import com.softnet.medical.model.Study;
import com.softnet.medical.services.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/study")
public class StudyController {
    private final StudyService studyService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudyById(@PathVariable Long id) {
        return studyService.findStudyByStudyId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public ResponseEntity<?> getStudies(@RequestParam(required = false, defaultValue = "0") int pageNo) {
     //  return ResponseEntity.ok(studyService.findAll(PageRequest.of(pageNo, 100, Sort.Direction.DESC, "createDate")));
       return ResponseEntity.ok(studyService.findAll(PageRequest.of(pageNo, 100, Sort.Direction.DESC, "createDate")));
    }

    @PostMapping
    public ResponseEntity<?> saveStudy(@RequestBody(required = false) StudyPatientDto form) {
        //validate study here and then save
        return new ResponseEntity<>(studyService.saveStudy(form), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody(required = false) StudyPatientDto form) {
        return studyService.findById(form.getId())
                .map(study -> ResponseEntity.ok(studyService.update(form)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        studyService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
