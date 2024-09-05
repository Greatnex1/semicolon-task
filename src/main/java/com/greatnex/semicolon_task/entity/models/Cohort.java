package com.greatnex.semicolon_task.entity.models;

import com.greatnex.semicolon_task.entity.models.users.Instructor;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@RequiredArgsConstructor
public class Cohort {

    @Id
    @GeneratedValue
    private Long id;

    private String cohortName;

    private  String description;

    private String dateCohortStarted;

    private String dateCohortEnded;

    private String cohortAvatar;

    private String scheduleAnEvent;

    private String announcement;

    private String shareResource;

    @OneToOne
   private Instructor instructor;

    @ElementCollection(fetch = FetchType.LAZY)
    @ToString.Exclude
    Set<String> listOfLearners = new HashSet<>();

   @OneToMany
   private List<Program> programList = new ArrayList<>();


    @OneToMany
     private List<Course> courseList = new ArrayList<>();


}
