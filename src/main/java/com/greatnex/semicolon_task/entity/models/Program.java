package com.greatnex.semicolon_task.entity.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nameOfProgram;
    @NotEmpty
    private String programDetails;
    @NotEmpty
    private String dateCreated;

    private int views;

}
