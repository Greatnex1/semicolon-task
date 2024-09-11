package com.greatnex.semicolon_task.entity.dtos;

import lombok.Data;

@Data
public class LoginResponseObject {

    private String accessToken;

    private String refreshToken;

    private Object user;
}
