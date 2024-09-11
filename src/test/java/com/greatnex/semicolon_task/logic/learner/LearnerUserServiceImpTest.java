package com.greatnex.semicolon_task.logic.learner;

import com.greatnex.semicolon_task.entity.dtos.LearnerDto;
import com.greatnex.semicolon_task.entity.models.users.Learner;
import com.greatnex.semicolon_task.repository.LearnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LearnerUserServiceImpTest {

    @Mock
    private LearnerRepository learnerRepository;

    @InjectMocks
    private LearnerUserServiceImp learnerUserServiceImp;

    @Test
    void createNewLearner() {
        when(learnerRepository.save(any())).thenReturn(new Learner());
        learnerUserServiceImp.createNewLearner(new LearnerDto());
    }

    @Test
    void updateLearnerProfile() {
        when(learnerRepository.save(any())).thenReturn(new Learner());
        learnerUserServiceImp.createNewLearner(new LearnerDto());

    }

    @Test
    void findAllLearners() {
        when(learnerRepository.save(any())).thenReturn(new Learner());
        learnerUserServiceImp.createNewLearner(new LearnerDto());
    }


    @Test
    void deleteLearnerByEmail() {
        when(learnerRepository.save(any())).thenReturn(new Learner());
        learnerUserServiceImp.createNewLearner(new LearnerDto());
    }

    @Test
    void deleteLearnerById() {
        when(learnerRepository.save(any())).thenReturn(new Learner());
        learnerUserServiceImp.createNewLearner(new LearnerDto());
    }
}
//void addRoleToUser(String username, String roleName);