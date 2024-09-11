package com.greatnex.semicolon_task.repository;

import com.greatnex.semicolon_task.entity.models.Cohort;
import com.greatnex.semicolon_task.exception.CohortAlreadyExistException;
import com.greatnex.semicolon_task.logic.cohort.CohortServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
    public void findCohortById() throws CohortAlreadyExistException {
        Cohort cohort = new Cohort();
        cohort.setId(1L);
        when(cohortRepository.findById(cohort.getId())).thenReturn(Optional.of(cohort));

   cohortService.findCohortById(cohort.getId());

    }
}