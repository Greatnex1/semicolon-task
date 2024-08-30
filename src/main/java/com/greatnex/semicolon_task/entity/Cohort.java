package com.greatnex.semicolon_task.entity;

import com.greatnex.semicolon_task.entity.users.Instructor;
import com.greatnex.semicolon_task.entity.users.Learner;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
