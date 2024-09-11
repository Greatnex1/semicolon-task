package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class InstructorDto {

   private  Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String gender;


    private boolean isActive;

 @NotBlank
    private String lastActivity;

    @NotEmpty
    public String organization;
}
