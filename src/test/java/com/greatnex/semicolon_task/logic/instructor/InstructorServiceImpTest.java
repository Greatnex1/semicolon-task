package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class InstructorServiceImpTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImp instructorServiceImp;

    private ModelMapper instructorMapper;

    @Test
    void createNewInstructor()  {

        Instructor instructor= new Instructor();
        InstructorDto instructorDto= new InstructorDto();
        instructor.setId(1L);
        instructorDto.setEmail("we@gmail.com");
        instructorRepository.save(instructor);
        when(instructorRepository.save(any())).thenReturn(instructor);
        assertThat(instructor).isNotNull();
       instructorServiceImp.createNewInstructor(new InstructorDto());
    }

    @Test
    void updateInstructorProfile() {
    }

    @Test
    void findInstructorById() {
        when(instructorRepository.save(any())).thenReturn(new Instructor());

        instructorServiceImp.createNewInstructor(new InstructorDto());
    }

    @Test
    void deleteInstructorByEmail() {
        when(instructorRepository.save(any())).thenReturn(new Instructor());

        instructorServiceImp.createNewInstructor(new InstructorDto());
    }

    @Test
    void deleteInstructorById() {
        when(instructorRepository.save(any())).thenReturn(new Instructor());

        instructorServiceImp.createNewInstructor(new InstructorDto());
    }
}