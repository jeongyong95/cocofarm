package com.jbnu.cocofarm.service.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.basket.BasketRepository;
import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.orders.OrdersTempRepository;
import com.jbnu.cocofarm.domain.user.User;
import com.jbnu.cocofarm.domain.user.UserRepository;
import com.jbnu.cocofarm.domain.user.UserDto.UserRequestDto;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * UserServiceImpl
 */
@AllArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private BasketRepository basketRepo;
    private OrdersTempRepository ordersTempRepo;

    @Override
    public void registerUser(UserRequestDto userRequestDto) {
        userRepo.save(userRequestDto.toEntity());
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
    public Boolean checkLogin(UserRequestDto userRequestDto) {
        Optional<User> findedUser = userRepo.findByEmail(userRequestDto.getEmail());
        if (findedUser.isPresent()) {
            if (!userRequestDto.getPassword().equals(findedUser.get().getPassword())) {
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
    public Boolean isAlreadyJoined(String email) {
        Optional<User> joiner = userRepo.findByEmail(email);
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