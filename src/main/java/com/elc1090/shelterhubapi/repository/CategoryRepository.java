package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
