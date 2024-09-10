package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long > {
    Optional<Program> findByNameOfProgram(String title);
    Optional<Program> deleteProgramByNameOfProgram(String title);
}
