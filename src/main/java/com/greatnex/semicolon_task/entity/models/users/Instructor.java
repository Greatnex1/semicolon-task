package com.greatnex.semicolon_task.entity.models.users;

import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Instructor  {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique = true, updatable = false)
      private String email;
    @NotEmpty
    private String organization;

    private String dateCreated;
    @NotEmpty
    private String lastActivity;

    private boolean active;

    @NotEmpty
    private String country;
    @NotEmpty
    private String state;

    private LocalDate dob;

    @NotEmpty
    private String avatarUrl;

    @OneToOne
    private Course course;

    @OneToMany
    private  List<Cohort> cohortList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @ToString.Exclude
     Set <String> courseList = new HashSet<>();

    // private PlaformUser platform_user;

}
