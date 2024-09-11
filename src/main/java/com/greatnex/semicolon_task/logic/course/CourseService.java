package com.greatnex.semicolon_task.logic.course;

import com.greatnex.semicolon_task.entity.dtos.CourseDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CourseService {

    Course createNewCourse(CourseDto courseDto ) throws Exception;

   Optional<Course>viewCourse (UUID id);

    Course updateCourse (UUID courseId, CourseDto courseDto) throws Exception;

   Set<String> assignInstructorToCourse(UUID id, InstructorDto instructorDto)
           ;
    Page<Course> findAllCourseByPagination(Pageable pageable);

    List<Course> getAllCourseWithoutPagination();

    void findCourseByTitle(String courseTitle);

    Optional<Course> findById(UUID Id);

//    List<Course> findAllCourse(UUID Id);

      void deleteCourse(UUID courseId);

   void deleteAllCourse();

}
