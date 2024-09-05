package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class LearnerDto {

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String email;

    private String gender;

    private String learnerAbout;

    private String location;

    private String country;

    private String state;

    private String dob;

    private String avatarUrl;



}
