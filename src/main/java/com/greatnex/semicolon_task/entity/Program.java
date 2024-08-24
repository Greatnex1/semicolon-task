package com.greatnex.semicolon_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;


@Entity
@Data
public class Program {
    @Id
    @GeneratedValue()


    private String nameOfProgram;

    private String programDetails;

    private String dateCreated;
}
