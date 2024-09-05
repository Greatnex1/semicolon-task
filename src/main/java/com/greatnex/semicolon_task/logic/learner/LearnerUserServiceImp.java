package com.greatnex.semicolon_task.logic.learner;

import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.exception.LearnerAlreadyExistException;
import com.greatnex.semicolon_task.exception.LearnerNotFoundException;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearnerUserServiceImp implements LearnerUserService{
      private final LearnerRepository learnerRepository;

      private ModelMapper learnerMapper;

    @Override
    public Learner createNewLearner(LearnerDto learnerDto) {

        Learner semicolonLearner = new Learner();
        semicolonLearner.setEmail(learnerDto.getEmail());
        semicolonLearner.setFirstName(learnerDto.getFirstname());
        semicolonLearner.setLastName(learnerDto.getLastname());
        semicolonLearner.setDateCreated(Instant.now().toString());

        Learner learner = learnerRepository.findByEmail(learnerDto.getEmail()).orElse(null);

                if (learner!=null){
           log.info("this email {} is already taken, please use another email",learner.getEmail());
           throw new LearnerAlreadyExistException("This email {} is already taken" + learner.getEmail());
        }
        return   learnerRepository.save(semicolonLearner);
    }

    @Override
    public List<Learner> findLearnerById() {

        return learnerRepository.findAll();
   }

    @Override
    public Learner findLearnerByEmail(String email) {

     return  learnerRepository.findByEmail(email).orElseThrow(
             ()-> new LearnerNotFoundException("Learner does not exist"));
    }

    @Override
    public Learner updateLearnerProfile(Long id, LearnerDto profile) {
        Learner learner = learnerRepository.findById(id).orElseThrow(()-> new LearnerNotFoundException("Learner not found"));
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
        Learner learner = learnerRepository.findByEmail(email).orElseThrow(
                () -> new LearnerNotFoundException("Learner account not found"));
                if (learner != null) {
            learnerRepository.deleteByEmail(email);
            log.info("Learner account removed");
        }
    }

    @Override
    public void deleteLearnerById(Long id) {
        learnerRepository.deleteById(id);
    }

}
