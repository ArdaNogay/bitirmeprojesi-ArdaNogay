package com.softtech.softtechspringboot.repository;

import com.softtech.softtechspringboot.entity.Category;
import com.softtech.softtechspringboot.Enum.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category ,Long> {

    Category findCategoryByCategoryType(CategoryType type);

}
