package com.softnet.medical.repository;

import com.softnet.medical.model.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
    @EntityGraph(attributePaths = "patient")
    Page<Study> findByOrderByCreateDateDesc(Pageable pageable);
}
