package com.greatnex.semicolon_task.entity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotEmpty
    private String courseTitle;
    @NotEmpty
    private String coursePeriod;
    @NotEmpty
    private String courseInformation;
    @NotEmpty
    private String quizzes;
    @NotEmpty
    private String polls;
    @NotEmpty
    private String learners ;

    @OneToOne
    private EnumModule enumModule;

//   @OneToMany
//    private Instructors listOfInstructors;
//


}
