package com.greatnex.semicolon_task.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

     @ManyToOne
     private Cohort cohort;


//     @ManyToOne
//     private List<Cohort> cohortList = new ArrayList<>();

    @ManyToMany
    private List<Program> listOfProgram = new ArrayList<>();

  // @ManyToMany
 //  private Course course;


}
