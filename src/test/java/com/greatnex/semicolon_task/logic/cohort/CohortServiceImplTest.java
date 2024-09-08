package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.entity.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.repository.CohortRepository;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
class CohortServiceImplTest {
    @InjectMocks
    private CohortServiceImpl cohortServiceImpl;

    @Mock
    private CohortRepository cohortRepository;

    @Mock
    private LearnerRepository learnerRepository;

    @MockBean
    private ModelMapper cohortMapper;

    @Test
    void createNewCohort() {
        Cohort cohort = new Cohort();
        when(cohortRepository.save(any())).thenReturn(cohort);
        assertThat(cohort).isNotNull();
        cohortServiceImpl.createNewCohort(new CohortDto());
    }

    @Test
    void findCohortById() {
        Cohort cohort = new Cohort();
        when(cohortRepository.findById(cohort.getId())).thenReturn(Optional.of(cohort));
        cohortServiceImpl.findCohortById(cohort.getId());
    }

    @Test
    void cohortCanBeRemoved() {
        Cohort cohort = new Cohort();
        doNothing().when(cohortRepository.findById(cohort.getId()));
        cohortRepository.deleteById(cohort.getId());
        cohortServiceImpl.deleteCohortById(cohort.getId());
    }

    @Test
    void inviteLearnerToCohort() {
        Cohort cohort = new Cohort();
        when(cohortRepository.save(any())).thenReturn(cohort);
        var savedCohort = cohortRepository.findById(cohort.getId());

        Learner learner = new Learner();
        when(learnerRepository.save(any())).thenReturn(learner);
        var savedLearner = learnerRepository.findById(learner.getId());

        if (savedLearner.isPresent() && savedCohort.isPresent()) {
            Cohort addLearner = savedCohort.get();
            addLearner.getListOfLearners().add(savedLearner.get().getEmail());
            cohortRepository.save(addLearner);
        }
    }
    @Test
    void updateCohort(){
        CohortDto cohortDto = new CohortDto();
        when(cohortRepository.save(any())).thenReturn(cohortDto);
        cohortMapper.map(cohortDto, cohortDto);
        cohortDto.setCohortAvatar(cohortDto.getCohortAvatar());
        cohortDto.setDescription(cohortDto.getDescription());
        cohortDto.setDateCohortStarted(cohortDto.getDateCohortStarted());
        log.info("cohort update-->{}", cohortDto);
        log.info("Update successful");
    }
}
