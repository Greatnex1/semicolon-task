package com.greatnex.semicolon_task.logic.learner;

import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.dtos.UserProfileDto;
import com.greatnex.semicolon_task.entity.Instructor;
import com.greatnex.semicolon_task.entity.Learner;
import com.greatnex.semicolon_task.exception.LearnerAlreadyExistException;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearnerUserServiceImp implements LearnerUserService{
      private final LearnerRepository learnerRepository;

      private ModelMapper learnerMapper;

    @Override
    public Learner createNewLearner(LearnerDto learnerDto) throws LearnerAlreadyExistException {

        Learner learner = learnerRepository.findByEmail(learnerDto.getEmail()).orElse(null);

        if (learner!=null){
           log.info("this email {} is already taken",learner.getEmail());
           throw new LearnerAlreadyExistException("This email {} is already taken" + learner.getEmail());
        }
        Learner semicolonLearner = new Learner();
        semicolonLearner.setEmail(learnerDto.getEmail().toLowerCase().trim());
        semicolonLearner.setFirstName(learnerDto.getFirstname());
        semicolonLearner.setLastName(learnerDto.getLastname());
        semicolonLearner.setDateCreated(Instant.now().toString());

        return   learnerRepository.save(semicolonLearner);
    }

    @Override
    public Optional<Learner> viewLearnerProfile(LearnerDto learnerDto) {

        return learnerRepository.findByEmail(learnerDto.getEmail());

   }

    @Override
    public Learner updateLearnerProfile(Long id, LearnerDto profile) throws Exception {
Learner learner = learnerRepository.findById(id).orElseThrow(()-> new Exception("Learner not found"));
if (learner!=null){
    learnerMapper.map(profile, learner);
    learner.setLearnerAbout(profile.getLearnerAbout());
    learner.setAvatar(profile.getAvatarUrl());

     learnerRepository.save(learner);
}
     return learner;
}

    @Override
    public Page<Learner> findAllLearners(Pageable pageable) {

        return learnerRepository.findAll(pageable);
    }

    @Override
    public void deleteLearnerByEmail(String email) {
        learnerRepository.findByEmail(email);
    }

    @Override
    public void deleteLearnerById(Long id) {
        learnerRepository.findById(id);
    }

//    @Transactional
//    Role createRoleIfNotFound(
//            String name) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role(name);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//
//    @Override
//    public void addRoleToUser(String username, String roleName) {
//        log.info("Adding role {} to user {}", roleName, username);
//        PlatformUser user = userRepository.findByUserNameIgnoreCase(username).get();
//        Role role = roleRepository.findByName(roleName);
//        user.getRoles().add(role);
//    }
}
