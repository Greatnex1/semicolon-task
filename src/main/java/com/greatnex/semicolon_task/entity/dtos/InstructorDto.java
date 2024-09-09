package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class InstructorDto {

   private  Long id;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    private String email;
 @NotEmpty
    private String gender;
 @NotEmpty
    private boolean isActive;
 @NotEmpty
    private String lastActivity;
 @NotEmpty
    public String Organization;
}
