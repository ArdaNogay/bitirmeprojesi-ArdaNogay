package com.softtech.softtechspringboot.Repository;

import com.softtech.softtechspringboot.Entity.Category;
import com.softtech.softtechspringboot.Enum.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category ,Long> {

    Category findByCategoryType(CategoryType type);

}
