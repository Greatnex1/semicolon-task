package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.logic.cohort.CohortService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CohortController {

    @GetMapping("/cohort")
    public String cohort(){
        return "Create a cohort!!";
    }
    @Autowired
    private  CohortService cohortService;

    @PostMapping("/cohort/new")
    public ResponseEntity<?> createCohort(@RequestBody CohortDto cohortDto) throws Exception {
        return new ResponseEntity<>(cohortService.createNewCohort(cohortDto), HttpStatus.CREATED);
    }

    @GetMapping("/cohorts")
    public ResponseEntity<Page<Cohort>> getAllCohortOnThePlatform(){
        return ResponseEntity.ok(cohortService.findAllCohort(Pageable.ofSize(10)));
    }
    @GetMapping("/cohort/{cohort_id}")
    public ResponseEntity<?> findCohortById(@PathVariable @NotBlank(message = "require valid parameter") Long cohort_id) {
        try {
            return ResponseEntity.ok(cohortService.findCohortById(cohort_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cohort/learner")
    public ResponseEntity<?> addLearnerToCohort(@RequestBody @Valid LearnerDto LearnerDto) throws Exception {
        return ResponseEntity.ok(cohortService.addLearnerToCohort(LearnerDto));
    }

//    @PatchMapping("/cohort/edit")
//    public ResponseEntity<Cohort> editCohort(@RequestBody CohortDto cohortDto,@RequestParam Long id ){
//
//        return ResponseEntity.ok(cohortService.updateCohort(id, cohortDto));
//    }

    @DeleteMapping("/cohort/delete/{cohort_id}")
    public ResponseEntity<?> deleteCohort(@PathVariable("cohort_id")Long cohort_id){
        cohortService.deleteCohort(cohort_id);
        return new ResponseEntity<>("Successfully delete post", HttpStatus.OK);
    }

}
