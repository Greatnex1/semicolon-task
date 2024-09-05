package com.greatnex.semicolon_task.entity.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PlatformUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique = true, updatable = false)
    private String email;

    private String organization;

    private String dateCreated;

    private String lastActivity;

    private boolean active;

    private String country;

    private String state;

    private LocalDate dob;

    private String avatarUrl;


//
//    @OneToMany
//    @JoinColumn(name = "platform_user_id")
//    @ToString.Exclude
//    Set<Role> roles = new HashSet<>();
}
