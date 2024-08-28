package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.exception.CohortException;
import com.greatnex.semicolon_task.repository.CohortRepository;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Slf4j
public class CohortServiceImplTest {

     @Mock
     private CohortRepository cohortRepository;

    @Mock
    private LearnerRepository learnerRepository;

    @InjectMocks
    private CohortServiceImpl cohortServiceImpl;



    @BeforeEach
            void up () {
      cohortServiceImpl = new CohortServiceImpl(cohortRepository, learnerRepository);

  }

    @Test
    public void testThatACohortCanBeCreated() throws Exception {

        CohortDto cohortDto = new CohortDto();

       // cohortDto.setId(1L);
        cohortDto.setCohortName("Peaky blinders");
        cohortDto.setDescription("This is a cohort for the rich in heart and not in items");
      //  cohortDto.setDateCohortStarted("June 2025");
        Cohort createdCohort = cohortServiceImpl.createNewCohort(cohortDto);
log.info("saved Cohort NAME ->{}", cohortDto.getCohortName());
        when(cohortRepository.save(any(Cohort.class))).thenReturn(createdCohort);
        assertNotNull(createdCohort);

//
//        CohortDto cohortDto = new CohortDto();
//        Cohort createdCohort = cohortServiceImpl.createNewCohort(cohortDto);
//
//
//
//        cohortRepository.save(createdCohort);
//        //log.info("Cohort with name -> {} has been created successfully", cohortDto.getCohortName());
//        assertThat(cohort.getId()).isNotNull();

    }

    @Test
   public  void CohortCanBeFoundById() throws CohortException {
        Cohort cohort = new Cohort();

        cohort.setId(1L);
        cohort.setCohortName("Peaky blinders");
        cohort.setDescription("This is a cohort for the rich in heart and not in items");
        cohort.setDateCohortStarted("June 2025");
        
        log.info("Cohort ID ->:{}", cohortRepository.findCohortById(1L));


        cohortRepository.save(cohort);

     //   when(cohortRepository.findById(1L)).thenReturn(Optional.of(cohort));

        Cohort cohortResult = cohortServiceImpl.findCohortById(1L);
//Your brain on porn
        assertNotNull(cohortResult);
        assertEquals(cohort.getId(), cohortResult.getId());
        assertEquals(cohort.getCohortName(), cohortResult.getCohortName());
        assertEquals(cohort.getDescription(), cohortResult.getDescription());

      // assertThat(cohortRepository).isNotNull();

      //  verify(cohortRepository, times(1)).findById(1L);
    }

    @Test
    public void findAllCohort() {



    }

    @Test
    public void addLearnerToCohort() {
    }

    @Test
    public void viewCohort() {
    }

    @Test
    public void updateCohort() {
    }

    @Test
    public void addInstructorToCohort() {
    }

    @Test
    public void deleteCohort() {
    }
}