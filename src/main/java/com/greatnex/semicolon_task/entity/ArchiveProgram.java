package com.greatnex.semicolon_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ArchiveProgram {
    @Id
    @GeneratedValue
    private Long id;

    private String nameOfProgram;

    private String programDetails;

    private String dateArchived;




}
