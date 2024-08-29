package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.Instructor;
import com.greatnex.semicolon_task.entity.Learner;
import com.greatnex.semicolon_task.exception.LearnerAlreadyExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface InstructorService {
    Instructor createNewInstructor(InstructorDto instructorDto ) throws Exception;

    Optional<Instructor> viewInstructorProfile(InstructorDto InstructorDto);

    Instructor updateInstructorProfile(Long userid, UserProfileDto profile) throws Exception;

    Page<Instructor> findAllInstructors(Pageable pageable);

    Optional<Instructor> findInstructorById(Long id);

    void deleteInstructorByEmail(String email);

    void deleteInstructorById(Long id);
}
