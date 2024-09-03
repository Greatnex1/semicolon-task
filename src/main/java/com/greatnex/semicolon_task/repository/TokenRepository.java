package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.LearnerInvitationToken;
import com.greatnex.semicolon_task.entity.users.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository <LearnerInvitationToken, Long> {

    Optional<LearnerInvitationToken> findByToken(String token);

//LearnerInvitationToken findByUserLearner(Learner learner);



}
