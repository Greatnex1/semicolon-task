package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.users.Learner;
import com.greatnex.semicolon_task.logic.learner.LearnerUserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LearnerController {

    @Autowired
    private LearnerUserServiceImp learnerUserServiceImp;

    @GetMapping("/learner")
    public String instructor(){
        return "No learner account created yet";
    }

    @PostMapping("/learner/new")
    public ResponseEntity<?> createLearner(@RequestBody LearnerDto learnerDto) throws Exception {
        return new ResponseEntity<>(learnerUserServiceImp.createNewLearner(learnerDto), HttpStatus.CREATED);
    }

    @GetMapping("/learners")
    public ResponseEntity<Page<Learner>> getAllLearnerOnThePlatform(){
        return ResponseEntity.ok(learnerUserServiceImp.findAllLearners(Pageable.ofSize(5)));
    }

    @DeleteMapping("/learner/delete/{learner_id}")
    public ResponseEntity<?> deleteLearnerById(@PathVariable("learner_id")Long learner_id){
        learnerUserServiceImp.deleteLearnerById(learner_id);
        return new ResponseEntity<>("Learner is removed", HttpStatus.OK);
    }
}
