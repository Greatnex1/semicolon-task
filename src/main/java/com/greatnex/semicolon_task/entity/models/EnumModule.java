package com.greatnex.semicolon_task.entity.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnumModule {
    @Id
    @GeneratedValue

    private Long id;

    private String session;




}
