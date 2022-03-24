package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.entity.Category;
import com.softtech.softtechspringboot.Enum.CategoryType;
import com.softtech.softtechspringboot.repository.CategoryDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryEntityService extends BaseEntityService<Category ,CategoryDao> {

    public CategoryEntityService(CategoryDao categoryDao) {
        super(categoryDao);
    }

    public Category findByCategoryType(CategoryType type){
        return getDao().findCategoryByCategoryType(type);
    }

}
