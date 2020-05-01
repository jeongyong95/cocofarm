package com.jbnu.cocofarm.service.category;

import com.jbnu.cocofarm.domain.category.Category;

/**
 * CategoryService
 */
public interface CategoryService {

    void registerCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Category category);

}