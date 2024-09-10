package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.models.InvitationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository <InvitationToken, Long> {

//    List<InvitationToken> findByToken(String token);

//LearnerInvitationToken findByUserLearner(Learner learner);



}
