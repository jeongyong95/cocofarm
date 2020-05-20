package com.jbnu.cocofarm.service.seller;

import com.jbnu.cocofarm.domain.seller.Seller;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerLoginDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerRegisterDto;
import com.jbnu.cocofarm.domain.seller.SellerDto.SellerSessionDto;

public interface SellerService {

    boolean registerSeller(SellerRegisterDto registerDto);

    Seller getSeller(Long id);

    SellerSessionDto checkSeller(SellerLoginDto loginDto);

}