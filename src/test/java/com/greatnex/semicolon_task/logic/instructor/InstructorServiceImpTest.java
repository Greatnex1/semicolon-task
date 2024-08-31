package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.users.Instructor;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorServiceImpTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorServiceImp instructorServiceImp;

    @Test
    void createNewInstructor() throws Exception {
        when(instructorRepository.save(any())).thenReturn(new Instructor());

        instructorServiceImp.createNewInstructor(new InstructorDto());
    }

    @Test
    void updateInstructorProfile() {
    }

    @Test
    void findInstructorById() {
    }

    @Test
    void deleteInstructorByEmail() {
    }

    @Test
    void deleteInstructorById() {
    }
}