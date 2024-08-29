package com.greatnex.semicolon_task.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
//@Builder
//@RequiredArgsConstructor
public class Cohort {
    @Id
    @GeneratedValue
    private Long id;

    private String cohortName;

    private  String description;

    private String dateCohortStarted;

    private String dateCohortEnded;

    private String cohortAvatar;

    private String learners;

    private String scheduleAnEvent;

    private String announcement;

    private String shareResource;

    @OneToOne
private Instructor instructor;

   @OneToMany
   private List<Program> programList = new ArrayList<>();

  @OneToMany
   private List<Instructor> instructorList = new ArrayList<>();

   @OneToMany
   private List<Learner> listOfLearners = new ArrayList<>();

@OneToMany
   private List<Course> courseList = new ArrayList<>();

//    @ElementCollection(fetch = FetchType.LAZY)
//    @ToString.Exclude
//    Set<String> instructors = new HashSet<>();

}
