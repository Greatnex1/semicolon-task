package com.greatnex.semicolon_task.logic.program;


import com.greatnex.semicolon_task.entity.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.models.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProgramService {

    Program createNewProgram(ProgramDto programDto ) ;
    Page<Program> findAllProgramsUsingPagination(Pageable pageable);

      Program archiveProgram(ProgramDto programDto);

     List<Program> findAllProgramsById(Long id);

    void deleteProgramById(Long id);

     void deleteProgramByTitle(String title);

     void deleteAllProgram();
}
