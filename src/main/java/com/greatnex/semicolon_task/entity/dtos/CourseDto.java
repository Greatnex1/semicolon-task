package com.greatnex.semicolon_task.entity.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CourseDto {

    private Long id;

    private String courseTitle;

    private String coursePeriod;

    private String quizzes;

    private String polls;
}
