package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.function.LongFunction;

@Data
public class CourseDto {

 //   private Long id;
 @NotEmpty
    private String courseTitle;
    @NotEmpty
    private String coursePeriod;
    @NotEmpty
    private String quizzes;
    @NotEmpty
    private String polls;
}
