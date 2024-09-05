package com.greatnex.semicolon_task.logic.program;

import com.greatnex.semicolon_task.entity.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.models.Program;
import com.greatnex.semicolon_task.exception.ProgramNotFoundException;
import com.greatnex.semicolon_task.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;

    private ModelMapper programMapper;

    @Override
    public Program createNewProgram(ProgramDto programDto) {
        Program program = new Program();

        program.setNameOfProgram(programDto.getNameOfProgram());
        program.setProgramDetails(programDto.getProgramDetails());
        program.setDateCreated(Instant.now().toString());

        log.info("Program Created!");

        return  programRepository.save(program);
    }

    @Override
    public Page <Program> findAllProgramsUsingPagination(Pageable pageable) {
        return programRepository.findAll(pageable);
    }

    @Override
        public Program archiveProgram(ProgramDto programDto)  {
        Program archiveProgram = programRepository.findById(programDto.getId()).orElseThrow(
                () -> new ProgramNotFoundException("This Program does not exist"));


        programMapper.map(archiveProgram, programDto);

//        archiveProgram.setNameOfProgram(programDto.getNameOfProgram());
//        archiveProgram.setProgramDetails(programDto.getProgramDetails());
//        archiveProgram.setDateArchived(Instant.now().toString());

      programRepository.save(archiveProgram);
//      archiveProgram.getArchives().add(archiveProgram);
//
//      programRepository.save(archiveProgram);

      log.info("Program Archived");

        return archiveProgram;

   }

    @Override
    public List<Program>  findAllProgramsById(Long id) {
        return programRepository.findAll();

    }

    @Override
    public void deleteProgramById(Long id) {
        programRepository.deleteById(id);

    }

    @Override
    public void deleteProgramByTitle(String title) {
        programRepository.findByNameOfProgram(title);
    }

    @Override
    public void deleteAllProgram() {
        programRepository.deleteAll();
    }
}
