package com.greatnex.semicolon_task.logic.learner;

import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.exception.LearnerAlreadyExistException;
import com.greatnex.semicolon_task.exception.LearnerNotFoundException;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpServletRequest;
//import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearnerUserServiceImp implements LearnerUserService{
      private final LearnerRepository learnerRepository;

      private ModelMapper learnerMapper;

    @Override
    public Learner createNewLearner(LearnerDto learnerDto) {
        if(learnerRepository.findByEmail(learnerDto.getEmail().toLowerCase()).isPresent()) {
            log.info("this email {} is already taken, please use another email",learnerDto.getEmail());
            throw new LearnerAlreadyExistException("There is a Learner account with  this detail");
        }

        Learner semicolonLearner = new Learner();
        semicolonLearner.setEmail(learnerDto.getEmail().toLowerCase().trim());
        isValidEmail(learnerDto.getEmail());
        semicolonLearner.setFirstName(learnerDto.getFirstname());
        semicolonLearner.setLastName(learnerDto.getLastname());
        semicolonLearner.setDateCreated(Instant.now().toString());
        semicolonLearner.setLearnerAbout(learnerDto.getLearnerAbout());
       // semicolonLearner.setCohort(new Cohort());
        semicolonLearner.setActive(true);

        return   learnerRepository.save(semicolonLearner);
    }

    public boolean  isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    @Override
    public List<Learner> getAllLearner() {

        return learnerRepository.findAll();
   }

    @Override
    public Optional<Learner> findById(Long id) {
        return learnerRepository.findById(id);
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
         learner.setDateCreated(Instant.now().toString());

        learnerRepository.save(learner);
          }
     return learner;
     }

    @Override
    public Page<Learner> findAllLearnersUsingPagination(Pageable pageable) {

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
