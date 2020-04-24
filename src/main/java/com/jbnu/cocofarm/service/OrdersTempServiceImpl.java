package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.OrdersTemp;
import com.jbnu.cocofarm.domain.user.OrdersTempRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * OrdersTempServiceImpl
 */
@AllArgsConstructor
@Service
public class OrdersTempServiceImpl implements OrdersTempService {

    private OrdersTempRepository ordersTempRepo;

    @Override
    public void registerOrdersTemp(OrdersTemp ordersTemp) {
        ordersTempRepo.save(ordersTemp);
    }

    @Override
    public void updateOrdersTemp(OrdersTemp ordersTemp) {
        ordersTempRepo.save(ordersTemp);
    }

    @Override
    public void deleteOrdersTemp(OrdersTemp ordersTemp) {
        ordersTempRepo.save(ordersTemp);
    }

}