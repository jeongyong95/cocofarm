package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.Basket;

/**
 * BasketService
 */
public interface BasketService {

    void registerBasket(Basket basket);

    void updateBasket(Basket basket);

    void deleteBasket(Basket basket);
}