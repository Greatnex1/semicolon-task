package com.greatnex.semicolon_task.entity.users;

import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Instructor {
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

    @OneToOne
    private Course course;

    @OneToMany
    private  List<Cohort> cohortList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @ToString.Exclude
     Set <String> courseList = new HashSet<>();

}
