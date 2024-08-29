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
        return "Create a cohort!!";
    }


    @PostMapping("/cohort/new")
    public ResponseEntity<?> createCohort(@RequestBody CohortDto cohortDto) throws Exception {
        return new ResponseEntity<>(cohortService.createNewCohort(cohortDto), HttpStatus.CREATED);
    }

    @GetMapping("/cohorts")
    public ResponseEntity<Page<Cohort>> getAllCohortOnThePlatform(){
        return ResponseEntity.ok(cohortService.findAllCohortByPagination(Pageable.ofSize(10)));
    }
//    @GetMapping("/cohort/{cohort_id}")
//    public ResponseEntity<?> findCohortById(@PathVariable @NotBlank(message = "require valid parameter") Long cohort_id) {
//        try {
//            return ResponseEntity.ok(cohortService.findCohortById(cohort_id));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    @GetMapping("/cohort/{cohort_id}")
    public ResponseEntity <List<Cohort>> findCohortByIdWithoutPagination(@PathVariable @NotBlank(message = "require valid parameter") Long cohort_id) {
        try {
            return ResponseEntity.ok(cohortService.findAllCohorts(cohort_id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
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
        return new ResponseEntity<>("Cohort deleted", HttpStatus.OK);
    }

}
