package com.greatnex.semicolon_task.entity.users;

import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.entity.Program;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Column(unique = true, updatable = false)
    private String email;

     private  String learnerAbout;

     private String dateCreated;

     private boolean active;

     private String lastActivity;

     private String avatar;

    @ElementCollection(fetch = FetchType.LAZY)
    @ToString.Exclude
   Set <String> listOfCohort = new HashSet<>();


    @ManyToMany
    private List<Program> listOfProgram = new ArrayList<>();




}
