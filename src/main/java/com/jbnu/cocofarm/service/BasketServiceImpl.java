package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.Basket;
import com.jbnu.cocofarm.domain.user.BasketRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * BasketServiceImpl
 */
@AllArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository basketRepo;

    @Override
    public void registerBasket(Basket basket) {
        basketRepo.save(basket);
    }

    @Override
    public void updateBasket(Basket basket) {
        basketRepo.save(basket);
    }

    @Override
    public void deleteBasket(Basket basket) {
        basketRepo.save(basket);
    }

}