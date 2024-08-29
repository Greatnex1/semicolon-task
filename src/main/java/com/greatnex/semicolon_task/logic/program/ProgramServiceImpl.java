package com.greatnex.semicolon_task.logic.program;

import com.greatnex.semicolon_task.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.ArchiveProgram;
import com.greatnex.semicolon_task.entity.Course;
import com.greatnex.semicolon_task.entity.Program;
import com.greatnex.semicolon_task.repository.ArchiveRepository;
import com.greatnex.semicolon_task.repository.ProgramRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;

    private final ArchiveRepository archiveRepository;

    @Override
    public Program createNewProgram(ProgramDto programDto) throws Exception {
        Program program = new Program();

        program.setNameOfProgram(programDto.getNameOfProgram());
        program.setProgramDetails(programDto.getProgramDetails());
        program.setDateCreated(Instant.now().toString());

        log.info("Program Created!");

        return  programRepository.save(program);
    }

    @Override
    public Page <Program> findAllPrograms(Pageable pageable) {
        return programRepository.findAll(pageable);
    }

    @Override
    public Program archiveProgram(ProgramDto programDto) throws Exception {
        Program program = programRepository.findById(programDto.getId()).orElseThrow(
                () -> new Exception("this PROGRAM does not exist"));

        ArchiveProgram archiveProgram = new ArchiveProgram();

        archiveProgram.setNameOfProgram(programDto.getNameOfProgram());
        archiveProgram.setProgramDetails(programDto.getProgramDetails());
        archiveProgram.setDateArchived(Instant.now().toString());

      archiveRepository.save(archiveProgram);
      program.getArchives().add(archiveProgram);

      programRepository.save(program);

      log.info("Program Archived");
        return program;


    }

    @Override
    public Program viewProgram(Long id) {
        Optional<Program> foundProgram = programRepository.findById(id);
        foundProgram.ifPresent(program -> {
            program.setViews(program.getViews() + 1);
            programRepository.save(program);
        });
        return foundProgram.get();
    }

    @Override
    public void deleteProgramById(Long id) {
        programRepository.deleteById(id);

    }

    @Override
    public void deleteProgramByName(String title) {
        programRepository.findByNameOfProgram(title);
    }
}
