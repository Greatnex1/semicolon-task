package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.ArchiveProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<ArchiveProgram, Long> {
}
