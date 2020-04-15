package com.jbnu.cocofarm.service;

import com.jbnu.cocofarm.domain.product.Category;

/**
 * CategoryService
 */
public interface CategoryService {

    void registerCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

}