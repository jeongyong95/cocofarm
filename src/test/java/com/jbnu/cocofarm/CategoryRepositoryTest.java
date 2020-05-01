package com.jbnu.cocofarm;

import com.jbnu.cocofarm.domain.category.Category;
import com.jbnu.cocofarm.domain.category.CategoryRepository;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootTest
public class CategoryRepositoryTest {

    private CategoryRepository repo;

    @Test
    public void insertCategoryTest() {
        Category category = new Category();
        category.setCategoryName("농산물");
        repo.save(category);
    }
}