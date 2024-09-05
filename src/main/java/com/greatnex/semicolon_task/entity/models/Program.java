package com.greatnex.semicolon_task.entity.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Program {
    @Id
    @GeneratedValue()


    private String nameOfProgram;

    private String programDetails;

    private String dateCreated;

    private int views;

//    @OneToMany
//    private List <ArchiveProgram> archives = new ArrayList<>();
}
