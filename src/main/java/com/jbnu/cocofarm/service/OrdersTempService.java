package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.OrdersTemp;

/**
 * OrdersTempService
 */
public interface OrdersTempService {

    void registerOrdersTemp(OrdersTemp ordersTemp);

    void updateOrdersTemp(OrdersTemp ordersTemp);

    void deleteOrdersTemp(OrdersTemp ordersTemp);
}