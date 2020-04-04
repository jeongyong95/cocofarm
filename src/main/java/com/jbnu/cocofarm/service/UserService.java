package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.User;

/**
 * UserService
 */
public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUser(String email);

    Boolean checkLogin(String email, String password);

    Boolean isAlreadyJoined(User user);

}