package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LearnerRepository extends JpaRepository<Learner, Long> {

    Optional<Learner> findByEmail(String email);

    void deleteByEmail(String email);

}
