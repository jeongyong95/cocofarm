package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.user.Seller;

public interface SellerService {

    void registerSeller(Seller seller);

    void updateSeller(Seller seller);

    void deleteSeller(Long id);

    Seller getSeller(String sellerCode);

    Boolean checkLogin(String sellerCode, String password);

    Boolean isAlreadyJoined(Seller seller);
}