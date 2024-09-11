package com.greatnex.semicolon_task.logic.course;

import com.greatnex.semicolon_task.entity.dtos.CourseDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.models.Course;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import com.greatnex.semicolon_task.exception.CohortAlreadyExistException;
import com.greatnex.semicolon_task.exception.CourseAlreadyExistException;
import com.greatnex.semicolon_task.exception.CourseNotFoundException;
import com.greatnex.semicolon_task.repository.CourseRepository;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Slf4j
@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    @Autowired
    private  CourseRepository courseRepository;

    private final InstructorRepository instructorRepository;

    private ModelMapper modelMapper;

    @Override
    public Course createNewCourse(CourseDto courseDto) throws CourseAlreadyExistException {
        if(courseRepository.findByCourseTitle(courseDto.getCourseTitle()).isPresent()) {
            log.info("this cohort name {} is already taken, please use another name for the cohort",courseDto.getCourseTitle());
            throw new CourseAlreadyExistException("There is a cohort account with  this detail");
        }
        Course course = new Course();

        course.setCourseTitle(courseDto.getCourseTitle());
        course.setCoursePeriod(courseDto.getCoursePeriod());
        //course.setId(UUID.fromString(UUID.randomUUID().toString()));
        course.setPolls(courseDto.getPolls());
        course.setQuizzes(courseDto.getQuizzes());

        log.info("Course Created!");

        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> viewCourse(UUID courseId) {


        return courseRepository.findById(courseId);

    }
    @Override
    public Course updateCourse(UUID courseId, CourseDto courseDto) throws Exception {
        var updateCourse = courseRepository.findById(courseId).orElseThrow(()-> new Exception("Course not found"));
        if(updateCourse != null) {
            modelMapper.map(courseDto, updateCourse);
            updateCourse.setCoursePeriod(courseDto.getCoursePeriod());
            updateCourse.setQuizzes(courseDto.getQuizzes());
             courseRepository.save(updateCourse);
        }
       return  updateCourse;
    }

    @Override
    public Set<String> assignInstructorToCourse(UUID id, InstructorDto instructorDto)  {
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
    public List<Course> getAllCourseWithoutPagination() {
        return courseRepository.findAll();
    }

    @Override
    public void findCourseByTitle(String courseTitle) {
        courseRepository.findByCourseTitle(courseTitle);
    }

    @Override
    public Optional<Course> findById(UUID id) {
        return courseRepository.findById(id);
    }

//    @Override
//    public List<Course> findCourseById(UUID id) {
//     return courseRepository.findCourseById(id);
//    }

    @Override
    public void deleteCourse(UUID courseId) {
        var courseExist = courseRepository.existsById(courseId);
        if(!courseExist){
            throw new IllegalStateException("course id "+ courseId + "not found");
        }
      courseRepository.deleteById(courseId);

    }

    @Override
    public void deleteAllCourse() {

        courseRepository.deleteAll();
    }
}
