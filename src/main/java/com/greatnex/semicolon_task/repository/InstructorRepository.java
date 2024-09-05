package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.models.users.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, Long > {

   Optional<Instructor> findInstructorByEmail(String email);

   Optional<Instructor> deleteInstructorByEmail(String email);
}

