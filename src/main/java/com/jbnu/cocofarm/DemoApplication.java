package com.jbnu.cocofarm;

import com.jbnu.cocofarm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private ProductRepository productRepository;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

}
