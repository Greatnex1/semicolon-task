package com.greatnex.semicolon_task.entity.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CohortDto {

     private Long id;

    @NotEmpty
    private String cohortName;

    @NotEmpty
    private  String description;

    @NotEmpty
    private String program;


    private String cohortAvatar;

    @NotEmpty
    private String scheduleAnEvent;

    @NotEmpty
    private String announcement;

    @NotEmpty
    private String shareResource;

    @NotEmpty
    private String dateCohortStarted;


}
