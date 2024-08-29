package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.Cohort;
import com.greatnex.semicolon_task.exception.CohortException;
import com.greatnex.semicolon_task.logic.cohort.CohortServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

//@SpringBootTest

@Slf4j
@ExtendWith({MockitoExtension.class})
//@AutoConfigureTestDatabase
//@DataJpaTest
public class CohortRepositoryTest {

//        @Autowired
//        private TestEntityManager entityManager;

        @Mock
        private  CohortRepository cohortRepository ;

        @InjectMocks
        private CohortServiceImpl cohortService;
//
//        @Test
//        public void testExample() throws Exception {
//            this.entityManager.persist(new User("sboot", "1234"));
//            User user = this.repository.findByUsername("sboot");
//            assertThat(user.getUsername()).isEqualTo("sboot");
//            assertThat(user.getVin()).isEqualTo("1234");
//        }

//    @Test
//    public void findByCohortName() {
//        this.entityManager.persist(new Cohort());
//        Cohort cohort = this.cohortRepository.findByCoohortName("Whimps");
//        assertNotNull("Cohort name ->{}", cohort.getCohortName());
//
//
//    }

    @Test
    public void findCohortById() throws CohortException {
        Cohort cohort = new Cohort();
        cohort.setId(1L);
        when(cohortRepository.findById(cohort.getId())).thenReturn(Optional.of(cohort));

   cohortService.findCohortById(cohort.getId());

    }
}