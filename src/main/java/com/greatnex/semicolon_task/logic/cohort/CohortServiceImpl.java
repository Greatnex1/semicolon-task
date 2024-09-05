package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.dtos.InstructorDto;
import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.users.Instructor;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.exception.CohortNotFoundException;
import com.greatnex.semicolon_task.logic.invitation.EmailService;
import com.greatnex.semicolon_task.repository.CohortRepository;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CohortServiceImpl implements CohortService{

    private final CohortRepository cohortRepository;

   private final LearnerRepository learnerRepository;

    private ModelMapper cohortMapper;

  //  private EmailService emailService;


    @Override
    public Cohort createNewCohort(CohortDto cohortDto)  {
     Cohort cohort = new Cohort();
     cohort.setCohortName(cohortDto.getCohortName());
     cohort.setDescription(cohortDto.getDescription());
     cohort.setDateCohortStarted(Instant.now().toString());
     cohort.setCohortAvatar(cohortDto.getCohortAvatar());

     log.info("Cohort Created successfully");
     return cohortRepository.save(cohort);

    }

    @Override
    public  Cohort findCohortById(Long id)  {

        return cohortRepository.findCohortById(id).orElseThrow(()-> new CohortNotFoundException("Cohort not Found"));

    }

    @Override
    public List<Cohort> findAllCohorts() {
        return cohortRepository.findAll();
    }

    @Override
    public Page<Cohort> findAllCohortByPagination(Pageable pageable) {
        return cohortRepository.findAll(pageable);
    }

//    @Override
//    public Cohort addLearnerToCohort(String cohortName, LearnerDto learnerDto) {
////        Cohort cohort = cohortRepository.findByCohortName(cohortName).orElseThrow(()-> new CohortNotFoundException("Cohort Not Found"));
//////
////
////        Learner student = cohort.getLearner();
////        student.getListOfCohort().add(learnerDto.getEmail());
//
////        learner.setEmail(learnerDto.getEmail());
////        learner.setFirstName(learnerDto.getLastname());
////        learner.setLastName(learnerDto.getLastname());
////        learner.setDateCreated(Instant.now().toString());
////        learner.setLearnerAbout(learnerDto.getLearnerAbout());
////
////        learner.setCohort(cohort);
////        learnerRepository.save(learner);
////        cohort.getListOfLearners().add(learner);
//
//        return cohortRepository.save(cohort);
//    }

    @Override
    public  Cohort viewCohort(Long id, CohortDto cohortDto) {
        Cohort savedCohort = cohortRepository.findById(id).orElseThrow(() -> new RuntimeException("Cohort not found"));
        log.info("cohort -> {}", savedCohort);
        if (savedCohort != null) {
            cohortMapper.map(cohortDto, savedCohort);
        }
        return savedCohort;
    }

        @Override
        public void editCohort (Long id, CohortDto cohortDto){
            if (cohortDto == null) {
                throw new NullPointerException("Cohort object cannot be null");
            }
            Cohort cohort = cohortRepository.findById(id).orElseThrow(() -> new RuntimeException("Cohort not found"));
            log.info("cohort -> {}", cohort);
            if (cohort != null) {
                cohortMapper.map(cohortDto, cohort);
                cohort.setCohortAvatar( cohortDto.getCohortAvatar());
                cohort.setDescription(cohortDto.getDescription());
                cohort.setDateCohortStarted(cohortDto.getDateCohortStarted());
                log.info("cohort update-->{}", cohort);
                log.info("Update successful");
                cohortRepository.save(cohort);
            } else {
                throw new NullPointerException();
            }
        }


    @Override
    public Cohort addInstructorToCohort(Long id)  {
        Cohort cohort = cohortRepository.findById(id)
                .orElseThrow(() -> new CohortNotFoundException("Cohort does not exist"));
        Instructor instructor = cohort.getInstructor();
        instructor.getCohortList().add(cohort);

        return cohortRepository.save(cohort);
    }

    @Override
    public boolean inviteLearnerToCohort(Long learnerId, Long cohortId) {
        Optional<Learner> learner = learnerRepository.findById(learnerId);
        Optional<Cohort> cohort = cohortRepository.findById(cohortId);

        if (learner.isPresent() && cohort.isPresent()) {
            Cohort updatedCohort = cohort.get();
            updatedCohort.getListOfLearners().add(learner.get().getEmail());
            cohortRepository.save(updatedCohort);

     // emailService.sendInvitationMessage();

            return true;
        }
        return false;
    }

//    private void sendInvitationEmail(Learner learner, Cohort cohort) {
//        String subject = "Invitation to Join Cohort: " + cohort.getCohortName();
//        String body = "Dear " + learner.getFirstName() + ",\n\n" +
//                "You have been invited to join the cohort: " + cohort.getCohortName() +
//                ". Please log in to your account to accept this invitation.\n\n" +
//                "Best regards,\nThe Enum Team";

       // emailService.sendEmail(instructor.getEmail(), subject, body);
       // sendInvitationEmail(learner.getEmail(),subject, body);
    //}

    @Override
    public void deleteCohortById(Long id) {
            cohortRepository.deleteById(id);
    }

    @Override
    public void deleteCohortByName(String cohort_name) {
        Cohort cohort =cohortRepository.findByCohortName(cohort_name).orElseThrow(
                ()-> new CohortNotFoundException("Cohort with name " + cohort_name +"does not exist" ));
        cohortRepository.deleteById(cohort.getId());
        log.info("cohort removed");

             }
}
