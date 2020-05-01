package com.jbnu.cocofarm.service.basket;

import com.jbnu.cocofarm.domain.basket.Basket;
import com.jbnu.cocofarm.domain.basket.BasketRepository;

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