package com.greatnex.semicolon_task.logic.program;

import com.greatnex.semicolon_task.entity.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.models.Program;
import com.greatnex.semicolon_task.exception.ProgramAlreadyExistException;
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
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService{

        private final ProgramRepository programRepository;

    private ModelMapper programMapper;

    @Override
    public Program createNewProgram(ProgramDto programDto) throws ProgramAlreadyExistException {
        if(programRepository.findById(programDto.getId()).isPresent()) {
            log.info("Program Already Exist" );
            throw new ProgramAlreadyExistException("There is a program already with this detail");
        }

        Program program = new Program();

        program.setNameOfProgram(programDto.getNameOfProgram());
        program.setProgramDetails(programDto.getProgramDetails());
        program.setDateCreated(Instant.now().toString());

        log.info("Program Created!");

        return  programRepository.save(program);
    }

    @Override
    public Page <Program> findAllProgramsUsingPagination(Pageable pageable) {
        var foundProgram = programRepository.findAll();
        if(foundProgram.isEmpty())
        {
            log.warn("No Program  exist");
            throw new ProgramNotFoundException("No program found");

        }

        return programRepository.findAll(pageable);
    }

    @Override
        public Program archiveProgram(ProgramDto programDto)  {
        var foundProgram = programRepository.findById(programDto.getId());
        if(foundProgram.isEmpty())
        {
            log.warn("No Program  exist");
            throw new ProgramNotFoundException("No program found");

        }
        Program archiveProgram = programRepository.findById(programDto.getId()).orElseThrow(
                () -> new ProgramNotFoundException("This Program does not exist"));


        programMapper.map(archiveProgram, programDto);


      programRepository.save(archiveProgram);


      log.info("Program Archived");

        return archiveProgram;

   }

    @Override
    public List<Program> findAllProgramsWithoutPagination() {
        var foundProgram = programRepository.findAll();
        if(foundProgram.isEmpty())
        {
            log.warn("No Program  exist");
            throw new ProgramNotFoundException("No program found");

        }
        return programRepository.findAll();
    }

    Optional<Program> findProgramById(Long id) throws ProgramNotFoundException {
        var foundProgram = programRepository.findById(id);
        if(foundProgram.isEmpty())
        {
            log.info("this program id does not exist -> {}", id);
            throw new ProgramNotFoundException("There is no program with this id");

        }
        return programRepository.findById(id);
    }

    @Override
    public void deleteProgramById(Long id) {
        var programExist = programRepository.existsById(id);
        if(!programExist){
            throw new IllegalStateException("program id "+ id + "not found");
        }

        programRepository.deleteById(id);

    }

    @Override
    public void deleteProgramByTitle(String title) {
        var foundProgram = programRepository.findByNameOfProgram(title);
        if(foundProgram.isEmpty())
        {
            log.warn("No Program by this name exist ->{}", title);
            throw new ProgramNotFoundException("No program found");

        }
        programRepository.findByNameOfProgram(title);
    }

    @Override
    public void deleteAllProgram() {
        var foundProgram = programRepository.findAll();
        if(foundProgram.isEmpty())
        {
            log.warn("No Program  exist");
            throw new ProgramNotFoundException("No program found");

        }
        programRepository.deleteAll();
    }
}
