package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.exception.CohortAlreadyExistException;
import com.greatnex.semicolon_task.logic.cohort.CohortService;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CohortController {

    private final CohortService cohortService;

    @Autowired
    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    @GetMapping("/cohort")
    public String cohort(){
        return "No cohort created yet, Create a cohort!!";
    }


    @GetMapping("/cohort/all")
    public ResponseEntity <List<Cohort>> getAllCohortWithoutPagination()  {
        try {
            return ResponseEntity.ok(cohortService.findAllCohorts());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cohorts")
    public ResponseEntity<Page<Cohort>> getAllCohortOnThePlatform(){
        return ResponseEntity.ok(cohortService.findAllCohortByPagination(Pageable.ofSize(10)));
    }

    @GetMapping("/cohort/{cohort_id}")
    public ResponseEntity<?> findCohortById(@PathVariable Long cohort_id ) throws CohortAlreadyExistException {
        return ResponseEntity.ok(cohortService.findCohortById(cohort_id));

    }
    @PostMapping("/cohort/new")
    public ResponseEntity<?> createCohort( @NotEmpty @RequestBody  CohortDto cohortDto) throws Exception {
        return new ResponseEntity<>(cohortService.createNewCohort(cohortDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/cohort/delete/{cohort_id}")
    public ResponseEntity<?> removeCohortById(@PathVariable("cohort_id")Long cohort_id){
        cohortService.deleteCohortById(cohort_id);
        return new ResponseEntity<>("Cohort deleted", HttpStatus.OK);
    }

    @DeleteMapping("/cohort/delete/{cohort_name}")
    public ResponseEntity<?> removeCohortByCohortName(@PathVariable("cohort_name")String cohort_name){
        cohortService.deleteCohortByName(cohort_name);
        return new ResponseEntity<>("Cohort deleted", HttpStatus.OK);
    }

}
