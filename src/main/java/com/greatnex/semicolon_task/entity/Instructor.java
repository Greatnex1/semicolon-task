package com.greatnex.semicolon_task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private Course course;

@ManyToMany
    private List<Course> courseList = new ArrayList<>();


}
