package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.models.Program;
import com.greatnex.semicolon_task.logic.program.ProgramService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProgramController {

   // @Autowired
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
    public ResponseEntity<Page<Program>> getAllProgramsOnThePlatform(){
        return ResponseEntity.ok(programService.findAllProgramsUsingPagination(Pageable.ofSize(5)));
    }

    @PutMapping("program/archive")
    public ResponseEntity<?> archiveProgram(ProgramDto programDto)  {
        return new ResponseEntity<>(programService.archiveProgram(programDto), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/program/delete/{program_id}")
    public ResponseEntity<?> deleteProgramById(@PathVariable("program_id")Long program_id){
       programService.deleteProgramById(program_id);
        return new ResponseEntity<>("Program removed ", HttpStatus.OK);
    }
}
