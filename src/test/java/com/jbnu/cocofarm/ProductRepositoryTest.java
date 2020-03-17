package com.jbnu.cocofarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.jbnu.cocofarm.domain.Product;
import com.jbnu.cocofarm.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repo;

    @Test
    public void insertTest() {
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setName("애플 아이패드 " + i);
            products.add(product);
        }
        repo.saveAll(products);

    }

    @Test
    public void updateTest() {
        Optional<Product> product = repo.findById(2l);
        product.ifPresent(targetProduct -> {
            targetProduct.setName("애플 맥북");
            repo.saveAndFlush(targetProduct);
            System.out.println(targetProduct.getName() + "으로 상품명이 변경되었습니다.");
        });
    }

    @Test
    public void deleteTest() {
        Optional<Product> product = repo.findById(2l);
        product.ifPresent(targetProduct -> {
            repo.delete(targetProduct);
            System.out.println(targetProduct.getName() + "이 삭제되었습니다.");
        });
    }

}