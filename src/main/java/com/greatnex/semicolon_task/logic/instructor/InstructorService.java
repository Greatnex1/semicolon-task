package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface InstructorService {
    Instructor createNewInstructor(InstructorDto instructorDto ) ;

    Optional<Instructor> viewInstructorProfile(InstructorDto InstructorDto);

    Instructor updateInstructorProfile(Long userid, UserProfileDto profile) throws Exception;

    Page<Instructor>getAllInstructorsUsingPagination(Pageable pageable);

    List<Instructor> getInstructors();

    Optional<Instructor> getInstructorById(Long id);

    void removeInstructorByEmail(String email);

    void deleteInstructorById(Long id);
}
