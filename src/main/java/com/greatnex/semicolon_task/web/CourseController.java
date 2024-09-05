package com.greatnex.semicolon_task.web;

import com.greatnex.semicolon_task.entity.dtos.CourseDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.models.Course;
import com.greatnex.semicolon_task.logic.course.CourseServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    private CourseServiceImpl courseService;

    @GetMapping("/course")
    public String course(){
          return "Create a new Course";

    }

    @PostMapping("/course/new")
    public ResponseEntity<?> createNewCourse(@RequestBody CourseDto courseDto) throws Exception {
        return new ResponseEntity<>(courseService.createNewCourse(courseDto), HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<Page<Course>> getAllCoursesOnThePlatform(){
        return ResponseEntity.ok(courseService.findAllCourseByPagination(Pageable.ofSize(5)));
    }

    @PutMapping("/course/instructor")
    public ResponseEntity<?> addInstructorToCourse(@RequestBody @Valid Long id, InstructorDto InstructorDto)  {
        return ResponseEntity.ok(courseService.assignInstructorToCourse(id, InstructorDto));
    }

    @GetMapping("/course/{course_id}")
    public ResponseEntity<?> findCourseById(@PathVariable @NotBlank(message = "require valid parameter") Long course_id) {
        try {
            return ResponseEntity.ok(courseService.findCourseById(course_id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/course/delete/{course_id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable("course_id")Long course_id){
        courseService.deleteCourse(course_id );
        return new ResponseEntity<>("Successfully removed Course", HttpStatus.OK);
    }
}
