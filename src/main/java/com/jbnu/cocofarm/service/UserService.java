package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.User;

/**
 * UserService
 */
public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    Boolean isAlreadyJoined(String email);
}