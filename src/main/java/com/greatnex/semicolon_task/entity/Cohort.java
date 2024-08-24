package com.greatnex.semicolon_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



//@Data
@Entity
@Data
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

   @OneToMany
   private List<Program> programList = new ArrayList<>();

  @OneToMany
   private List<Instructor> instructorList = new ArrayList<>();

   @OneToMany
   private List<Learner> listOfLearners = new ArrayList<>();

@OneToMany
   private List<Course> courseList = new ArrayList<>();

}
