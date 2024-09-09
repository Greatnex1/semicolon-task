package com.greatnex.semicolon_task.entity.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProgramDto {

    private Long id;

    @NotEmpty
    private String nameOfProgram;
  @NotEmpty
    private String programDetails;
}
