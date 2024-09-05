package com.greatnex.semicolon_task.entity.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    private UUID id;

    private String courseTitle;

    private String coursePeriod;

    private String courseInformation;

    private String quizzes;

    private String polls;

    private String learners ;

    @OneToOne
    private EnumModule enumModule;

//   @OneToMany
//    private Instructors listOfInstructors;
//


}
