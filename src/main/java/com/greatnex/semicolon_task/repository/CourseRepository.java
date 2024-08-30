package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseTitle(String courseTitle);

}
