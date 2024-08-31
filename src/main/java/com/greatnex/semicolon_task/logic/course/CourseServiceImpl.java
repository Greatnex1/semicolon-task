package com.greatnex.semicolon_task.logic.course;

import com.greatnex.semicolon_task.dtos.CourseDto;
import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.Course;
import com.greatnex.semicolon_task.entity.users.Instructor;
import com.greatnex.semicolon_task.exception.CourseNotFoundException;
import com.greatnex.semicolon_task.repository.CourseRepository;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    private ModelMapper modelMapper;

    @Override
    public Course createNewCourse(CourseDto courseDto) {

        Course course = new Course();

        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCoursePeriod(courseDto.getCoursePeriod());
        course.setId(UUID.randomUUID());
        course.setPolls(courseDto.getPolls());
        course.setQuizzes(courseDto.getQuizzes());

        log.info("Course Created!");

        return courseRepository.save(course);
    }

    @Override
    public Course viewCourse(Long courseId, CourseDto courseDto) {

        Course viewCourse = courseRepository.findById(courseId).orElseThrow(
                () -> new CourseNotFoundException("Course not found"));
        if (viewCourse != null) {
            modelMapper.map(courseDto, viewCourse);

        }
        return viewCourse;
    }


    @Override
    public Course updateCourse(Long courseId, CourseDto courseDto) throws Exception {
        Course updateCourse = courseRepository.findById(courseId).orElseThrow(()-> new Exception("Course not found"));
        if(updateCourse != null) {
            modelMapper.map(courseDto, updateCourse);
            updateCourse.setCoursePeriod(courseDto.getCoursePeriod());
             courseRepository.save(updateCourse);
        }
       return  updateCourse;
    }

    @Override
    public Set<String> assignInstructorToCourse(Long id, InstructorDto instructorDto)  {
       Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException("Course does not exist"));

        Instructor instructor = new Instructor();

        instructor.setEmail(instructorDto.getEmail());
        instructor.setFirstName(instructorDto.getLastname());
        instructor.setLastName(instructorDto.getLastname());
        instructor.setDateCreated(Instant.now().toString());
        instructor.setLastActivity(instructorDto.getLastActivity());
       instructor.setOrganization(instructorDto.getOrganization());


        instructor.setCourse(course);
        instructorRepository.save(instructor);
        instructor.getCourseList().add(course.getCourseTitle());
        return instructor.getCourseList();
    }

    @Override
    public Page<Course> findAllCourseByPagination(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public void findCourseByTitle(String courseTitle) {
        courseRepository.findByCourseTitle(courseTitle);
    }

    @Override
    public Optional<Course> findCourseById(Long id) {

        return courseRepository.findById(id);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);

    }

    @Override
    public void deleteAllCourse() {
        courseRepository.deleteAll();
    }
}
