package com.greatnex.semicolon_task.logic.invitation;

import com.greatnex.semicolon_task.dtos.LearnerDto;

import javax.mail.MessagingException;

public interface EmailService {

   void sendInvitationMessage (LearnerDto learnerDto, String token)throws  MessagingException;

    void newInvitationTokenMail(String email, String token);
}
