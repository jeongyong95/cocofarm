package com.jbnu.cocofarm.service.orders;

import javax.transaction.Transactional;

import com.jbnu.cocofarm.domain.orders.OrdersTemp;
import com.jbnu.cocofarm.domain.orders.OrdersTempRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * OrdersTempServiceImpl
 */
@AllArgsConstructor
@Transactional
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