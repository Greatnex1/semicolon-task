package com.greatnex.semicolon_task.logic.instructor;

import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import com.greatnex.semicolon_task.exception.InstructorAlreadyExistException;
import com.greatnex.semicolon_task.exception.InstructorNotFoundException;
import com.greatnex.semicolon_task.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InstructorServiceImp implements InstructorService{

    private final InstructorRepository instructorRepository;


    private ModelMapper instructorMapper;


    @Override
    public Instructor createNewInstructor(InstructorDto instructorDto)  {
   Instructor instructor = new Instructor();
        instructor.setEmail(instructorDto.getEmail());
        instructor.setFirstName(instructorDto.getFirstname());
        instructor.setLastName(instructorDto.getLastname());
        //instructor.setActive(true);

        Instructor savedInstructor = instructorRepository.findInstructorByEmail(instructorDto.getEmail().toLowerCase()).orElse(null);

        if (savedInstructor != null){
            log.warn("Instructor with this email already exist :{} ", instructorDto.getEmail());
            throw new InstructorAlreadyExistException("Instructor with this email already exist " + instructorDto.getEmail());

        }

        return instructorRepository.save(instructor);
    }

    @Override
    public Optional<Instructor> viewInstructorProfile(InstructorDto instructorDto) {

        return instructorRepository.findInstructorByEmail(instructorDto.getEmail());
    }

    @Override
    public Instructor updateInstructorProfile(Long instructor_id, UserProfileDto profile)  {
        Instructor instructor = instructorRepository.findById(instructor_id).orElseThrow(()-> new InstructorNotFoundException("Instructor account does not exist"));
        if(instructor!= null){
            instructorMapper.map(profile,instructor);
            instructor.setCountry(profile.getCountry());
            instructor.setState(profile.getState());
            instructor.setAvatarUrl(profile.getAvatarUrl());
        }
        return instructor;
    }
    @Override
    public Page<Instructor> getAllInstructorsUsingPagination(Pageable pageable) {
        return instructorRepository.findAll(pageable);
    }

    @Override
    public List<Instructor> getInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    @Override
    public void removeInstructorByEmail(String email) {
        instructorRepository.deleteInstructorByEmail(email);}

    @Override
    public void deleteInstructorById(Long id) {
        instructorRepository.deleteById(id);
    }
}

//
//    String role = "User";
//        if(roleRepository.findByName(role) == null){
//                user.getRoles().add(roleRepository.save(new Role("User")));
//                }else {
//                user.getRoles().add(roleRepository.findByName(role));
//                }