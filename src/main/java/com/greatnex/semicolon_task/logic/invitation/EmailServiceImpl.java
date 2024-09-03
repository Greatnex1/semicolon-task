package com.greatnex.semicolon_task.logic.invitation;

import com.greatnex.semicolon_task.dtos.LearnerDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateEngineException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

        private final JavaMailSender mailSender;

        private final TemplateEngine templateEngine;

        @Value("${app.baseUrl}")
        private String baseUrl;

//        @Value("${app.clientUrl}")
//        private String clientUrl;


        @Override
        @Async
        public void sendInvitationMessage(LearnerDto request, String token) throws MessagingException {
            String link = baseUrl + "/v1/user/learner/invitation?token=" +token;
            Context ctx = new Context(Locale.getDefault());
            ctx.setVariable("req", request.getFirstname());
            ctx.setVariable("link", link);
            String html = templateEngine.process("welcome", ctx);
            getEmailResponse(request, html);
        }


    private void getEmailResponse(LearnerDto request, String html) {
        EmailResponse response = new EmailResponse();
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom("info@semicolon.africa");
            helper.setText(html, true);
            helper.setTo(request.getEmail());
            helper.setSubject("Invitation");

            mailSender.send(message);

            response.setMessage("mail send to: " + request.getEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | TemplateEngineException e) {
            response.setMessage("Mail sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);

        }
    }

    @Override
    public void newInvitationTokenMail(String email, String token) {

        String link = baseUrl + "/v1/user/verify?token=" +token;
        final SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(email);
        mailMessage.setSubject("New Invitation Link!");
        mailMessage.setFrom("info@semicolon.africa");
        mailMessage.setText(
                "Please click on the below link to accept our invitation. Link expires in 24 hours" + "\n" + link);
        mailSender.send(mailMessage);
    }

}
