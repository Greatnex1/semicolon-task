package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor,Long > {

   Optional<Instructor> findByEmail(String email);

   Optional<Instructor> deleteByEmail(String email);
}

