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
    public void registerUser(User user) {
        repo.save(user);
    }

    @Override
    public void updateUser(User user) {
        repo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> findedUser = repo.findById(id);
        findedUser.ifPresent(target -> {
            repo.delete(target);
        });
    }

    @Override
    public User getUser(String email) {
        Optional<User> findedUser = repo.findByEmail(email);
        return findedUser.get();
    }

    @Override
    public Boolean checkLogin(String email, String password) {
        Optional<User> findedUser = repo.findByEmail(email);
        User user = new User();
        if (findedUser.isPresent()) {
            user = findedUser.get();
            if (!password.equals(user.getPassword())) {
                System.out.println("다르당");
                return false;
            }
            System.out.println("로그인 검증 통과");
            return true;
        }
        System.out.println("아이디가 존재하지 않습니다.");
        return false;
    }

    @Override
    public Boolean isAlreadyJoined(User user) {
        Optional<User> joiner = repo.findByEmail(user.getEmail());
        if (joiner.isPresent()) {
            System.out.println("이미 존재하는 아이디입니다.");
            return true;
        }
        return false;
    }

}