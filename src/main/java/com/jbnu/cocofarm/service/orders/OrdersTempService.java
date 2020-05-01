package com.jbnu.cocofarm.service.orders;

import com.jbnu.cocofarm.domain.orders.OrdersTemp;

/**
 * OrdersTempService
 */
public interface OrdersTempService {

    void registerOrdersTemp(OrdersTemp ordersTemp);

    void updateOrdersTemp(OrdersTemp ordersTemp);

    void deleteOrdersTemp(OrdersTemp ordersTemp);
}