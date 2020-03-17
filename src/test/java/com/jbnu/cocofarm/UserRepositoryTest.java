package com.jbnu.cocofarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.User;
import com.jbnu.cocofarm.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Test
    public void insertTest() {
        List<User> users = new ArrayList<User>();
        for (int i = 1; i < 11; i++) {
            User user = new User();
            user.setUserId("testID" + i);
            user.setUserPw("testPW" + i);
            users.add(user);
        }
        repo.saveAll(users);
    }

    @Test
    public void updateTest() {
        Optional<User> user = repo.findById("testID1");
        user.ifPresent(selectedUser -> {
            selectedUser.setUserPw("vmfhgod11");
            repo.save(selectedUser);
        });
    }

    @Test
    public void deleteTest() {
        Optional<User> user = repo.findById("testID1");
        user.ifPresent(selectedUser -> {
            repo.delete(selectedUser);
        });
    }
}