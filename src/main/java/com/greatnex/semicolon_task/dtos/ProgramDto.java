package com.greatnex.semicolon_task.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProgramDto {

    private Long id;

    private String nameOfProgram;

    private String programDetails;
}
