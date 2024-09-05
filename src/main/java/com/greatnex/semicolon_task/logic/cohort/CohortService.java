package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.exception.CohortException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CohortService {

    Cohort createNewCohort(CohortDto cohortDto ) throws Exception;

     Cohort viewCohort(Long id, CohortDto cohortDto);

     void editCohort(Long id,  CohortDto cohortDto);



    Cohort findCohortById(Long id) throws CohortException;

    List <Cohort> findAllCohorts() ;

    Page<Cohort> findAllCohortByPagination(Pageable pageable);

   // Cohort addLearnerToCohort(String cohort_name, LearnerDto learnerDto);

   // Cohort addInstructorToCohort(InstructorDto instructorDto) throws Exception;
    Cohort addInstructorToCohort(Long id) throws Exception;
    boolean inviteLearnerToCohort(Long learnerId, Long cohortId);

    void deleteCohortById(Long id);

    void deleteCohortByName(String cohort_name);

    //  Optional <Cohort> viewCohort(CohortDto cohortDto);
//List <Cohort> viewCohort(CohortDto cohortDto);

    //  Optional <CohortDto> viewCohort(Long id) throws Exception;

    //   Cohort viewCohort(Long id) ;
}
