package com.greatnex.semicolon_task.logic.program;


import com.greatnex.semicolon_task.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
public interface ProgramService {

    Program createNewProgram(ProgramDto programDto ) throws Exception;
    Page<Program> findAllPrograms(Pageable pageable);

      Program archiveProgram(ProgramDto programDto) throws Exception;

     Program viewProgram(Long id);

    void deleteProgramById(Long id);

    public void deleteProgramByName(String title);
}
