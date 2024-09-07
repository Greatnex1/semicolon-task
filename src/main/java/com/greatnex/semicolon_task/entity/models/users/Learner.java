package com.greatnex.semicolon_task.entity.models.users;

import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.PlatformUser;
import com.greatnex.semicolon_task.entity.models.Program;
import com.greatnex.semicolon_task.entity.models.ValidEmail;
import jakarta.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

      @Email( message = "Invalid email address")
      @NotBlank
      @ValidEmail
      private String email;

     private  String learnerAbout;

     private String dateCreated;

     private boolean active;

     private String lastActivity;

     private String avatar;

     @ManyToOne
    private Cohort cohort;

//    @ManyToMany
//    private List<Course> listOfCourse = new ArrayList<>();
//
    @ManyToMany
    private List<Program> listOfProgram = new ArrayList<>();


    // private PlatformUser platform_user;


}
