package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import com.greatnex.semicolon_task.exception.InstructorNotFoundException;
import com.greatnex.semicolon_task.logic.instructor.InstructorServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class InstructorController {

    private InstructorServiceImp instructorService;


    @GetMapping("/instructor")
    public String instructor(){
        return "Create an instructor account";
    }

    @GetMapping("/instructors")
    public ResponseEntity<Page<Instructor>> getAllInstructorOnThePlatform(){
        return ResponseEntity.ok(instructorService.getAllInstructorsUsingPagination(Pageable.ofSize(5)));
    }


    @GetMapping("/instructors/{instructor_id}")
    public ResponseEntity<Optional<Instructor>> getInstructorById(@PathVariable Long id){
        return ResponseEntity.ok(instructorService.getInstructorById(id));
    }

    @GetMapping("/instructors/all")
    public ResponseEntity<List<Instructor>> getInstructors(){
        return ResponseEntity.ok(instructorService.getInstructors());
    }

    @PostMapping("/instructor/new")
    public ResponseEntity<?> createInstructor(@RequestBody @Valid InstructorDto instructorDto)  {
        return new ResponseEntity<>(instructorService.createNewInstructor(instructorDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/instructor/profile-edit")
    public ResponseEntity<?> updateInstructorProfile(@RequestParam Long instructorId, @RequestBody UserProfileDto userProfileDto) {
        try {
            return new ResponseEntity<>(instructorService.updateInstructorProfile(instructorId, userProfileDto), HttpStatus.OK);
        }
        catch (InstructorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/instructor/delete/{instructor_id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable("instructor_id")Long instructor_id){
        instructorService.deleteInstructorById(instructor_id);
        return new ResponseEntity<>("Instructor Successfully removed", HttpStatus.OK);
    }

    @DeleteMapping("/instructor/delete/{instructor_email}")
    public ResponseEntity<?> deleteInstructorByEmail(@PathVariable("instructor_email")String instructor_email){
        instructorService.removeInstructorByEmail(instructor_email);
        return new ResponseEntity<>("Instructor Successfully removed by email", HttpStatus.OK);
    }
}
