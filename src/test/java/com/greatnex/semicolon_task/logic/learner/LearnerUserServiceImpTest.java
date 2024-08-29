package com.greatnex.semicolon_task.logic.learner;

import com.greatnex.semicolon_task.repository.LearnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LearnerUserServiceImpTest {

    @InjectMocks
    private LearnerUserServiceImp learnerUserServiceImp;

    @Mock
    private LearnerRepository learnerRepository;

    @Test
    void createNewLearner() {
    }

    @Test
    void updateLearnerProfile() {
    }

    @Test
    void findAllLearners() {
    }

    @Test
    void deleteLearnerByEmail() {
    }

    @Test
    void deleteLearnerById() {
    }
}