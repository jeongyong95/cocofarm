package com.jbnu.cocofarm.service.seller;

import com.jbnu.cocofarm.domain.seller.Seller;

public interface SellerService {

    void registerSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Long id);

    Seller getSeller(String sellerCode);

    Boolean checkLogin(String sellerCode, String password);

    Boolean isAlreadyJoined(Seller seller);
}