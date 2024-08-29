package com.greatnex.semicolon_task.logic.cohort;

import com.greatnex.semicolon_task.dtos.CohortDto;
import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.exception.CohortException;
import com.greatnex.semicolon_task.repository.CohortRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CohortServiceImplTest {

    @InjectMocks
    private CohortServiceImpl cohortServiceImpl;

    @Mock
    private CohortRepository cohortRepository;

    @Test
    void createNewCohort() throws Exception {
        when(cohortRepository.save(any())).thenReturn(new Cohort());
        cohortServiceImpl.createNewCohort(new CohortDto());
    }

    @Test
    void findCohortById() throws CohortException {
        Cohort cohort = new Cohort();
       // when(cohortRepository.save(any())).thenReturn(new Cohort());
        when(cohortRepository.findCohortById(cohort.getId())).thenReturn(Optional.of(cohort));
        cohortServiceImpl.findCohortById(cohort.getId());
    }
    @Test
    void cohortCanBeRemoved(){
        Cohort cohort = new Cohort();
        cohortRepository.deleteAll();
        cohortServiceImpl.deleteCohort(cohort.getId());
        assertThat(cohort).isNull();
    }
}
