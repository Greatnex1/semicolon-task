package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.entity.users.Instructor;
import com.greatnex.semicolon_task.entity.users.Learner;
import com.greatnex.semicolon_task.exception.CohortNotFoundException;
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

//I still need to check through view cohort, findbyid and adding instructor to cohort.
@Slf4j
@Service
@RequiredArgsConstructor
public class CohortServiceImpl implements CohortService{

    private final CohortRepository cohortRepository;

   private final LearnerRepository learnerRepository;

    private ModelMapper cohortMapper;


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

    @Override
    public Cohort addLearnerToCohort(LearnerDto learnerDto) {
        Cohort cohort = cohortRepository.findByCohortName(learnerDto.getEmail()).orElseThrow(()-> new CohortNotFoundException("Cohort Not Found"));

        Learner learner = new Learner();

        learner.setEmail(learnerDto.getEmail());
        learner.setFirstName(learnerDto.getLastname());
        learner.setLastName(learnerDto.getLastname());
        learner.setDateCreated(Instant.now().toString());
        learner.setLearnerAbout(learnerDto.getLearnerAbout());

        learner.setCohort(cohort);
        learnerRepository.save(learner);
        cohort.getListOfLearners().add(learner);

        return cohortRepository.save(cohort);
    }

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
        public void updateCohort (Long id, CohortDto cohortDto){
            if (cohortDto == null) {
                throw new NullPointerException("Cohort object cannot be null");
            }
            Cohort cohort = cohortRepository.findById(id).orElseThrow(() -> new RuntimeException("Cohort not found"));
            log.info("cohort -> {}", cohort);
            if (cohort != null) {
                cohortMapper.map(cohortDto, cohort);
                cohort.setCohortName(cohortDto.getCohortName());
                cohort.setDescription(cohortDto.getDescription());
                log.info("cohort-->{}", cohort);
                log.info("Update successful");
                cohortRepository.save(cohort);
            } else {
                throw new NullPointerException();
            }
        }


    @Override
    public Cohort addInstructorToCohort(InstructorDto instructorDto) throws Exception {
        Cohort cohort = cohortRepository.findByCohortName(instructorDto.getEmail())
                .orElseThrow(() -> new Exception("Cohort does not exist"));
        Instructor instructor = cohort.getInstructor();
        instructor.getCohortList().add(cohort);

        return cohortRepository.save(cohort);
    }

    @Override
    public void deleteCohort(Long id) {
            cohortRepository.deleteById(id);
    }

    @Override
    public void deleteCohort(String cohort_name) {
        Cohort cohort =cohortRepository.findByCohortName(cohort_name).orElseThrow(
                ()-> new CohortNotFoundException("Cohort with name " + cohort_name +"does not exist" ));
        cohortRepository.deleteById(cohort.getId());

             }
}
