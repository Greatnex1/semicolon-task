package com.greatnex.semicolon_task.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue
    private UUID id;

    private String courseTitle;

    private String coursePeriod;

    private String quizzes;

    private String polls;

    private String learners ;

//   @OneToMany
//    private Instructors listOfInstructors;
//


}
