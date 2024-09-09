package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.exception.InstructorNotFoundException;
import com.greatnex.semicolon_task.exception.LearnerAlreadyExistException;
import com.greatnex.semicolon_task.logic.learner.LearnerUserServiceImp;

import javax.mail.MessagingException;
//import javax.validation.Valid;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
//import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class LearnerController {

@Autowired
    private LearnerUserServiceImp learnerUserServiceImp;

    @GetMapping("/learner/status")
    public String status(){

        return "No Learner created.";
    }


    @PostMapping("/learner/new")
    public ResponseEntity<?> createLearner(@Valid @RequestBody  LearnerDto learnerDto, BindingResult bindingResult)  {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(e -> {

                System.out.println(e.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body("Validation failed");
        }
        learnerUserServiceImp.isValidEmail(learnerDto.getEmail());
        return new ResponseEntity<>(learnerUserServiceImp.createNewLearner(learnerDto), HttpStatus.CREATED);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @GetMapping("/learners")
    public ResponseEntity<Page<Learner>> getAllLearnerOnThePlatformUsingPagination(){
        return ResponseEntity.ok(learnerUserServiceImp.findAllLearnersUsingPagination(Pageable.ofSize(5)));
    }

    @GetMapping("/learners/all")
    public ResponseEntity<List<Learner>>getAllLearnerOnThePlatform(){
        return ResponseEntity.ok(learnerUserServiceImp.getAllLearner());
    }

    @GetMapping("/learners/{learner_id}")
    public ResponseEntity<Optional<Learner>>getAllLearnerById(@PathVariable("learner_id")Long id){
        return ResponseEntity.ok(learnerUserServiceImp.findById(id));
    }

    @PutMapping("/user/learner/profile-edit")
    public ResponseEntity<?> updateLearnerProfile(@RequestParam Long learnerId, @RequestBody LearnerDto learnerDto) {
        try {
            return new ResponseEntity<>(learnerUserServiceImp.updateLearnerProfile(learnerId, learnerDto), HttpStatus.OK);
        }
        catch (InstructorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/learner/delete/{learner_id}")
    public ResponseEntity<Learner> deleteLearnerById(@PathVariable("learner_id")Long learner_id){
        learnerUserServiceImp.deleteLearnerById(learner_id);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
