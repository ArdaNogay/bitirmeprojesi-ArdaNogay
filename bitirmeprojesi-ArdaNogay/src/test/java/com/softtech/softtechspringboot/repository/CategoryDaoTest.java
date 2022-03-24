package com.softtech.softtechspringboot.repository;

import com.softtech.softtechspringboot.Enum.CategoryType;
import com.softtech.softtechspringboot.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    void findCategoryByCategoryType() {
        Category result = categoryDao.findCategoryByCategoryType(CategoryType.FOOD);
        assertNotNull(result);
        assertEquals(CategoryType.FOOD, result.getCategoryType());
    }
}