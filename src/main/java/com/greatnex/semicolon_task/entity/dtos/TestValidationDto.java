package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TestValidationDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String gender;


    @NotBlank
    private String learnerAbout;

    @NotBlank
    private String location;

    @NotBlank
    private String country;

    @NotBlank
    private String state;

    @NotBlank
    private String dob;

    @NotEmpty
    public String Organization;



}
