package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.users.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface InstructorService {
    Instructor createNewInstructor(InstructorDto instructorDto ) ;

    Optional<Instructor> viewInstructorProfile(InstructorDto InstructorDto);

    Instructor updateInstructorProfile(Long userid, UserProfileDto profile) throws Exception;

    Page<Instructor> findAllInstructorsUsingPagination(Pageable pageable);

    Optional<Instructor> findInstructorById(Long id);

    void deleteInstructorByEmail(String email);

    void deleteInstructorById(Long id);
}
