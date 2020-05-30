package com.jbnu.cocofarm.service.delivery;

import com.jbnu.cocofarm.domain.delivery.DeliveryDto.DeliveryRegisterDto;

/**
 * DeliveryService
 */
public interface DeliveryService {

    void registerDelivery(DeliveryRegisterDto registerDto, Long orderProductId);
}