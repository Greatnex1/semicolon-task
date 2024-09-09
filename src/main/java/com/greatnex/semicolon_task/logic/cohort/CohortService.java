package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.exception.CohortAlreadyExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CohortService {

    Cohort createNewCohort(CohortDto cohortDto ) throws Exception;

     Cohort viewCohort(Long id, CohortDto cohortDto);

     void editCohort(Long id,  CohortDto cohortDto);



    Optional<Cohort> findCohortById(Long id) throws CohortAlreadyExistException;

    List <Cohort> findAllCohorts() ;

    Page<Cohort> findAllCohortByPagination(Pageable pageable);

    Cohort addInstructorToCohort(Long id) throws Exception;

    boolean inviteLearnerToCohort(Long learnerId, Long cohortId);

   // Cohort inviteLearnerToCohort(LearnerDto learnerDto, CohortDto cohortDto) throws MessagingException;
    void deleteCohortById(Long id);

    void deleteCohortByName(String cohort_name);


}
