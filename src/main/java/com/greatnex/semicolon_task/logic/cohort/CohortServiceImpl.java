package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.dtos.InstructorDto;
import com.greatnex.semicolon_task.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.entity.Instructor;
import com.greatnex.semicolon_task.entity.Learner;
import com.greatnex.semicolon_task.exception.CohortException;
import com.greatnex.semicolon_task.repository.CohortRepository;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

//I still need to check through view cohort, findbyid and adding instructor to cohort.
@Slf4j
@Service
@RequiredArgsConstructor
public class CohortServiceImpl implements CohortService{

    private final CohortRepository cohortRepository;

  // private final InstructorRepository instructorRepository;

   private final LearnerRepository learnerRepository;

    private ModelMapper cohortMapper;

//    @Autowired
//    public CohortServiceImpl(CohortRepository cohortRepository) {
//        this.cohortRepository = cohortRepository;
//
//    }
//    @Autowired
//    public CohortServiceImpl(CohortRepository cohortRepository, LearnerRepository learnerRepository) {
//        this.cohortRepository = cohortRepository;
//        this.learnerRepository = learnerRepository;
//    }


    @Override
    public Cohort createNewCohort(CohortDto cohortDto) throws Exception {
     Cohort cohort = new Cohort();
     cohort.setCohortName(cohortDto.getCohortName());
     cohort.setDescription(cohortDto.getDescription());
     cohort.setDateCohortStarted(Instant.now().toString());
     cohort.setCohortAvatar(cohortDto.getCohortAvatar());

     log.info("Cohort Created successfully");
     return cohortRepository.save(cohort);

    }

    @Override
    public  Cohort findCohortById(Long id) throws CohortException {

        return cohortRepository.findCohortById(id).orElseThrow(()-> new CohortException("Cohort not Found"));

    }

    @Override
    public List<Cohort> findAllCohorts(Long id) {
        return cohortRepository.findAll();
    }

    @Override
    public Page<Cohort> findAllCohortByPagination(Pageable pageable) {
        return cohortRepository.findAll(pageable);
    }

    @Override
    public Cohort addLearnerToCohort(LearnerDto learnerDto) throws Exception {
        Cohort cohort = cohortRepository.findById(learnerDto.getId()).orElseThrow(()-> new Exception("Cohort Not Found"));

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
    public Optional <Cohort> viewCohort(Long id)  {
    Optional <Cohort> savedCohort = cohortRepository.findById(id);
    if (savedCohort.isPresent()){
        log.warn("Cohort details ->{}", savedCohort.getClass());
       // cohortMapper.

    }
//    if savedCohort.isPresent(cohort ->{
//        cohort.set
//                }
//                )
//      cohortRepository.save(savedCohort);

return savedCohort;

    }

    @Override
    public void updateCohort(Long id, CohortDto cohortDto) {
         if (cohortDto == null) {
            throw new NullPointerException("Cohort dto cannot be null");
        }
        Cohort cohort = cohortRepository.findById(id).orElse(null);
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

//        Cohort cohort = new Cohort();
//        cohort.setCohortName(cohort.getCohortName());
//        cohort.setDescription(cohort.getDescription());
//        cohort.setDateCohortStarted(Instant.now().toString());
//        cohort.setDateCohortEnded(Instant.now().toString());
//        cohort.setCohortAvatar(cohort.getCohortAvatar());
//
//         learner = learnerRepository.findByEmail(learnerDto.getEmail()).orElseThrow(
//                ()-> new Exception("User with this email does not exist"));

        return cohortRepository.save(cohort);
    }

    @Override
    public void deleteCohort(Long id) {
      cohortRepository.deleteById(id);
    }
}
