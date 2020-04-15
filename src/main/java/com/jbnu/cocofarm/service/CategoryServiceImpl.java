package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.product.Category;
import com.jbnu.cocofarm.domain.product.CategoryRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * CategoryServiceImpl
 */
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepo;

    @Override
    public void registerCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepo.delete(category);
    }

}