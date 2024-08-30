package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.exception.CohortException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface CohortService {

    Cohort createNewCohort(CohortDto cohortDto ) throws Exception;


   // Optional <Cohort> viewCohort(Long id);

   // List <Cohort> viewCohort(CohortDto cohortDto);

     Cohort viewCohort(Long id, CohortDto cohortDto);

     void updateCohort(Long id,  CohortDto cohortDto);



    Cohort findCohortById(Long id) throws CohortException;

    List <Cohort> findAllCohorts() ;

    Page<Cohort> findAllCohortByPagination(Pageable pageable);

    Cohort addLearnerToCohort(LearnerDto learnerDto) throws Exception;

    Cohort addInstructorToCohort(InstructorDto instructorDto) throws Exception;

    void deleteCohort(Long id);

    void deleteCohort(String name);

    //  Optional <Cohort> viewCohort(CohortDto cohortDto);
//List <Cohort> viewCohort(CohortDto cohortDto);

    //  Optional <CohortDto> viewCohort(Long id) throws Exception;

    //   Cohort viewCohort(Long id) ;
}
