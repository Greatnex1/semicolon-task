package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.users.Instructor;
import com.greatnex.semicolon_task.exception.InstructorNotFoundException;
import com.greatnex.semicolon_task.logic.instructor.InstructorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InstructorController {

    @Autowired
    private InstructorServiceImp instructorService;


    @GetMapping("/instructor")
    public String instructor(){
        return "Create an instructor account";
    }

    @PostMapping("/instructor/new")
    public ResponseEntity<?> createInstructor(@RequestBody InstructorDto instructorDto) throws Exception {
        return new ResponseEntity<>(instructorService.createNewInstructor(instructorDto), HttpStatus.CREATED);
    }

    @GetMapping("/instructors")
    public ResponseEntity<Page<Instructor>> getAllInstructorOnThePlatform(){
        return ResponseEntity.ok(instructorService.findAllInstructorsUsingPagination(Pageable.ofSize(5)));
    }

    @PatchMapping("/user/instructor/profile-edit")
    public ResponseEntity<?> updateInstructorProfile(@RequestParam Long instructorId, @RequestBody UserProfileDto userProfileDto) {
        try {
            return new ResponseEntity<>(instructorService.updateInstructorProfile(instructorId, userProfileDto), HttpStatus.OK);
        }
        catch (InstructorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/instructor/delete/{instructor_id}")
    public ResponseEntity<?> deleteInstructor(@PathVariable("instructor_id")Long instructor_id){
        instructorService.deleteInstructorById(instructor_id);
        return new ResponseEntity<>("Instructor Successfully removed", HttpStatus.OK);
    }
}
