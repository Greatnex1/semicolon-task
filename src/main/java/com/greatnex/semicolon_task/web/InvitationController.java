package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.logic.cohort.CohortServiceImpl;
import com.greatnex.semicolon_task.logic.invitation.EmailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;

@Controller
public class InvitationController {

    private EmailServiceImpl emailService;

    private CohortServiceImpl cohortService;

    @PostMapping("user/learner/invitation")

    public ResponseEntity<String> inviteLearnerToCohort(@RequestBody LearnerDto learnerDto,  String token){
        try{
            emailService.sendInvitationMessage(learnerDto, token);
            return ResponseEntity.ok("Invitation Sent Successfully");
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Failed to send invitation: " +e.getMessage());
        }
    }

    @PostMapping("/cohort/learner")
    public ResponseEntity<?> InviteLearnerToCohort(@RequestBody @Valid Long cohortId, Long LearnerId) {
        return ResponseEntity.ok(cohortService.inviteLearnerToCohort(cohortId, LearnerId));

    }


}
