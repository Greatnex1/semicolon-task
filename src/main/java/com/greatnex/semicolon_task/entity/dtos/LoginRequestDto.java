package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
