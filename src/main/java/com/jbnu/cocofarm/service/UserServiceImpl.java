package com.jbnu.cocofarm.service;

import java.util.Optional;

import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.domain.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public void saveUser(User user) {
        repo.save(user);
        System.out.println("회원가입 성공");
    }

    @Override
    public void updateUser(User user) {
        repo.save(user);
        System.out.println("회원수정 성공");
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> findedUser = repo.findById(id);
        findedUser.ifPresent(target -> {
            repo.delete(target);
            System.out.println("회원탈퇴 성공");
        });
    }

    @Override
    public Boolean isAlreadyJoined(String email) {
        User myUser = repo.findByEmail(email);
        if (myUser.getEmail() == email) {
            return true;
        }
        return false;
    }

}