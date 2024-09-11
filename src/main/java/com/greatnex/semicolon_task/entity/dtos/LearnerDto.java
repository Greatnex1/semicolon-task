package com.greatnex.semicolon_task.entity.dtos;

//import com.greatnex.semicolon_task.entity.models.ValidEmail;
//import jakarta.persistence.Entity;
//import jakarta.validation.Valid;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LearnerDto {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String gender;


    @NotEmpty
     private String learnerAbout;

    @NotEmpty
     private String location;

    @NotEmpty
     private String country;

    @NotEmpty
     private String state;

    @NotBlank
    private String dob;

    @NotEmpty
    private String avatarUrl;



}
