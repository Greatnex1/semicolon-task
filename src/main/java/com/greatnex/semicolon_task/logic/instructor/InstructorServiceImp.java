package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.Instructor;
import com.greatnex.semicolon_task.entity.Learner;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructorServiceImp implements InstructorService{

    private final InstructorRepository instructorRepository;


    private ModelMapper instructorMapper;


    @Override
    public Instructor createNewInstructor(InstructorDto instructorDto) throws Exception {

        Instructor instructor = instructorRepository.findByEmail(instructorDto.getEmail()).orElse(null);

        if (instructor!= null){
            log.warn("Instructor with this email already exist :{} ", instructorDto.getEmail());
            throw new Exception("Instructor with this email already exist " + instructorDto.getEmail());
        }
        instructor.setEmail(instructorDto.getEmail());
        instructor.setFirstName(instructorDto.getFirstname());
        instructor.setLastName(instructorDto.getLastname());
        instructor.setActive(true);

        return instructorRepository.save(instructor);
    }

    @Override
    public Optional <Instructor> viewInstructorProfile(InstructorDto instructorDto) {

        return instructorRepository.findByEmail(instructorDto.getEmail());
    }

    @Override
    public Instructor updateInstructorProfile(Long instructor_id, UserProfileDto profile) throws Exception {
        Instructor instructor = instructorRepository.findById(instructor_id).orElseThrow(()-> new Exception("Instructor account does not exist"));
        if(instructor!= null){
            instructorMapper.map(profile,instructor);
            instructor.setCountry(profile.getCountry());
            instructor.setState(profile.getState());
            instructor.setAvatarUrl(profile.getAvatarUrl());
        }
        return instructor;
    }
    @Override
    public Page<Instructor> findAllInstructors(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }

    @Override
    public Optional<Instructor> findInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    @Override
    public void deleteInstructorByEmail(String email) {
        instructorRepository.deleteByEmail(email);}

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.findById(id);
    }
}
