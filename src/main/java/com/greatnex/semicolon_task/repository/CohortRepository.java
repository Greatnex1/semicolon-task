package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.models.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CohortRepository extends JpaRepository<Cohort, Long> {

    List<Cohort> findCohortById(Long id);



    Optional<Cohort> findByCohortName(String cohortName);


}
