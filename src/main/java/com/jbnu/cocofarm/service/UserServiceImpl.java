package com.jbnu.cocofarm.service;

import java.util.List;
import java.util.Optional;

import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.BasketRepository;
import com.jbnu.cocofarm.domain.user.OrdersTemp;
import com.jbnu.cocofarm.domain.user.OrdersTempRepository;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.domain.user.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * UserServiceImpl
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private BasketRepository basketRepo;
    private OrdersTempRepository ordersTempRepo;

    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> findedUser = userRepo.findById(id);
        findedUser.ifPresent(target -> {
            userRepo.delete(target);
        });
    }

    @Override
    public User getUser(String email) {
        Optional<User> findedUser = userRepo.findByEmail(email);
        return findedUser.get();
    }

    @Override
    public Boolean checkLogin(String email, String password) {
        Optional<User> findedUser = userRepo.findByEmail(email);
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
        Optional<User> joiner = userRepo.findByEmail(user.getEmail());
        if (joiner.isPresent()) {
            System.out.println("이미 존재하는 아이디입니다.");
            return true;
        }
        return false;
    }

    @Override
    public List<Basket> getMyBasket(User user) {
        List<Basket> resultList = basketRepo.findByUser(user);
        return resultList;
    }

    @Override
    public List<OrdersTemp> getMyOrdersTemp(User user) {
        List<OrdersTemp> resultList = ordersTempRepo.findByUser(user);
        return resultList;
    }

}