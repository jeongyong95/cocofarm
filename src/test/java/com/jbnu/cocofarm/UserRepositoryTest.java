package com.jbnu.cocofarm;

import com.jbnu.cocofarm.domain.user.UserRepository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootTest
public class UserRepositoryTest {

    private UserRepository repo;

    @AfterAll
    public void clear() {
        repo.deleteAll();
    }

    @Test
    public void insertTest() {

    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }
}