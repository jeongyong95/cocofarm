package com.jbnu.cocofarm.service.category;

import com.jbnu.cocofarm.domain.category.Category;
import com.jbnu.cocofarm.domain.category.CategoryRepository;

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