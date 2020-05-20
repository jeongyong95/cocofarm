package com.jbnu.cocofarm.service.order;

import java.util.List;

import com.jbnu.cocofarm.domain.order.dto.OrderProductDto.OrderProductRegisterDto;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;

public interface OrderService {

    void registerOrder(OrderTotalRegisterDto totalRegisterDto, List<OrderProductRegisterDto> productRegisterDtoList,
            Long CustomerId);

    void cancelOrder(Long id);
}