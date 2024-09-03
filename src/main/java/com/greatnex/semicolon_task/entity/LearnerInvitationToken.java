package com.greatnex.semicolon_task.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Calendar;
import java.util.Date;


@Data
public class LearnerInvitationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private String userLearner;

    private Date expiryDate = calculateExpiryDate();

    public LearnerInvitationToken(String token, String userLearner) {
        this.token = token;
        this.userLearner = userLearner;
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, LearnerInvitationToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

}
