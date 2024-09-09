package com.greatnex.semicolon_task.entity.models.users;

import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.PlatformUser;
import com.greatnex.semicolon_task.entity.models.Program;
//import com.greatnex.semicolon_task.entity.models.ValidEmail;
//import jakarta.persistence.*;
//import javax.validation.Valid;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String firstName;


    private String lastName;


      @Column(unique = true, updatable = false)
      private String email;

     private  String learnerAbout;

     private String dateCreated;

     private boolean active;

     private String lastActivity;

     private String avatar;

    @ManyToMany
    private List<Program> listOfProgram = new ArrayList<>();


    // private PlatformUser platform_user;


}
