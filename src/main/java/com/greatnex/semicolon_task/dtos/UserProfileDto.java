package com.greatnex.semicolon_task.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfileDto {

    private String userName;

    private String location;

    private String country;

    private String state;

    private LocalDate dob;

    private String avatarUrl;
}
