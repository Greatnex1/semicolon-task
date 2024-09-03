package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CohortRepository extends JpaRepository<Cohort, Long> {

    Optional <Cohort> findCohortById(Long id);

  //   Cohort findById(Long id);

    Optional<Cohort> findByCohortName(String cohortName);



}
