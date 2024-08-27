package com.greatnex.semicolon_task.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CourseDto {

    private String courseTitle;

    private String coursePeriod;

    private String quizzes;

    private String polls;
}
