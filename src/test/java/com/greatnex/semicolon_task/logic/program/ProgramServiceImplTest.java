package com.greatnex.semicolon_task.logic.program;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.dtos.ProgramDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.Program;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.repository.ProgramRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.annotation.Transient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@Slf4j
class ProgramServiceImplTest {

    @InjectMocks
private ProgramServiceImpl programService;

    @Mock
    private ProgramRepository programRepository;

    @Test
    void createNewProgram() throws ProgramAlreadyExistException {
        Program program = new Program();
        program.setId(1L);
        when(programRepository.save(any())).thenReturn(program);
        assertThat(program).isNotNull();
        programService.createNewProgram(new ProgramDto());
    }

    @Test
    void archiveProgram() {
        Program program = new Program();
        program.setId(1L);
        programRepository.save(program);
        ProgramDto programDto = new ProgramDto();

        assertThat(program).isNotNull();

    }

    @Test
    void deleteProgramById() {
        Program program = new Program();

        when(programRepository.save(any())).thenReturn(program);

        programRepository.save(program);


        programRepository.deleteById(program.getId());
        log.info("Cohort id ->{}", program.getId());
        assertThat(program.getId()).isNull();


        verify(programRepository).deleteById(program.getId());


    }

    @Test
    void deleteProgramByName() {
        Program program = new Program();

        when(programRepository.save(any())).thenReturn(program);

        programRepository.save(program);

        assertThat(program.getNameOfProgram()).isNull();

        programRepository.deleteProgramByNameOfProgram(program.getNameOfProgram());

    }
}