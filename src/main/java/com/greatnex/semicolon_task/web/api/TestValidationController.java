package com.greatnex.semicolon_task.web.api;


import com.greatnex.semicolon_task.entity.dtos.TestValidationDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestValidationController {


    @PostMapping("/validate")
    public ResponseEntity<?> testValidator(@Valid @RequestBody TestValidationDto testDto) {

        System.out.println(testDto.getFirstName());
        System.out.println(testDto.getLastName());
        System.out.println(testDto.getEmail());
        System.out.println(testDto.getGender());
        System.out.println(testDto.getDob());
        System.out.println(testDto.getLearnerAbout());
        System.out.println(testDto.getOrganization());
        System.out.println(testDto.getCountry());
        System.out.println(testDto.getLocation());


        return ResponseEntity.ok(testDto);
    }
}
