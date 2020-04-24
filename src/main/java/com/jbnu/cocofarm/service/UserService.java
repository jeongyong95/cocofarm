package com.jbnu.cocofarm.service;

import java.util.List;

import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.OrdersTemp;
import com.jbnu.cocofarm.domain.user.User;

/**
 * UserService
 */
public interface UserService {

    void registerUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUser(String email);

    Boolean checkLogin(String email, String password);

    Boolean isAlreadyJoined(User user);

    List<Basket> getMyBasket(User user);

    List<OrdersTemp> getMyOrdersTemp(User user);

}