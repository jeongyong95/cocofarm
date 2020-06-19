package com.jbnu.cocofarm.service.order;

import java.util.List;

import com.jbnu.cocofarm.domain.order.OrderProduct;
import com.jbnu.cocofarm.domain.order.OrderTotal;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductDisplayDto;
import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalDisplayDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;

public interface OrderService {

    void registerOrder(OrderTotalRegisterDto totalRegisterDto, List<OrderProductRegisterDto> productRegisterDtoList,
            Long CustomerId);

    OrderProduct getOrderProduct(Long orderProductId);

    OrderTotal getOrderTotal(Long orderTotalId);

    void cancelOrder(Long id);

    OrderProductDisplayDto findByOrderProductId(Long orderProductId);

}