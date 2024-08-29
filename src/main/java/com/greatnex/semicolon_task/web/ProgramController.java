package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.Program;
import com.greatnex.semicolon_task.logic.program.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/program")
    public String course(){
        return "Create a new Program";

    }

    @PostMapping("/program/new")
    public ResponseEntity<?> createNewProgram(@RequestBody ProgramDto programDto) throws Exception {
        return new ResponseEntity<>(programService.createNewProgram(programDto), HttpStatus.CREATED);
    }

    @GetMapping("/programs")
    public ResponseEntity<Page<Program>> getAllCoursesOnThePlatform(){
        return ResponseEntity.ok(programService.findAllPrograms(Pageable.ofSize(5)));
    }

    @DeleteMapping("/program/delete/{program_id}")
    public ResponseEntity<?> deleteProgramById(@PathVariable("program_id")Long program_id){
       programService.deleteProgramById(program_id);
        return new ResponseEntity<>("Program removed ", HttpStatus.OK);
    }
}
