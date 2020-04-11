package com.jbnu.cocofarm;

import com.jbnu.cocofarm.domain.product.ProductRepository;
import com.jbnu.cocofarm.domain.user.Seller;
import com.jbnu.cocofarm.domain.user.SellerRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private SellerRepository seller;

    @Test
    public void insertTest() {
        Seller apple = new Seller();
        apple.setAccount("74180200218973");
        apple.setSellerCode("950312");
        apple.setPassword("vmfhgod11");
        seller.save(apple);
    }
}