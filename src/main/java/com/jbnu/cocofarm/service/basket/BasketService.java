package com.jbnu.cocofarm.service.basket;

import com.jbnu.cocofarm.domain.basket.Basket;

/**
 * BasketService
 */
public interface BasketService {

    void registerBasket(Basket basket);

    void updateBasket(Basket basket);

    void deleteBasket(Basket basket);
}