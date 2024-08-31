package com.greatnex.semicolon_task.logic.course;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.dtos.CourseDto;
import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CourseService {

    Course createNewCourse(CourseDto courseDto ) throws Exception;

Course viewCourse(Long id, CourseDto courseDto);

    Course updateCourse (Long courseId, CourseDto courseDto) throws Exception;

//   Course assignInstructorToCourse(InstructorDto instructorDto) throws Exception;

   Set<String> assignInstructorToCourse(Long id, InstructorDto instructorDto) ;
    Page<Course> findAllCourseByPagination(Pageable pageable);

    void findCourseByTitle(String courseTitle);

    Optional <Course> findCourseById(Long Id);

    void deleteCourse(Long courseId);

   void deleteAllCourse();

}
