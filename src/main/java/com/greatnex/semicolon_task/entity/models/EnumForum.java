package com.greatnex.semicolon_task.entity.models;

import com.greatnex.semicolon_task.entity.models.users.Learner;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class EnumForum {
    @Id
    @GeneratedValue
    private Long id;


    private String post;

 @ManyToOne
    private Learner learner;

    private String likes;

    private String comments;

    private String dateCreated;

    private String reply;





}
