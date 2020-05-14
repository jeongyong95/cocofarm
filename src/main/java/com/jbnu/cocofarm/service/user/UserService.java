package com.jbnu.cocofarm.service.user;

import java.util.List;

import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.domain.user.UserDto.UserRequestDto;

/**
 * UserService
 */
public interface UserService {

    void registerUser(UserRequestDto userRequestDto);

    void updateUser(User user);

    void deleteUser(Long id);

    User getUser(String email);

    Boolean checkLogin(UserRequestDto userRequestDto);

    Boolean isAlreadyJoined(String email);

    List<Basket> getMyBasket(User user);

    List<OrdersTemp> getMyOrdersTemp(User user);

}