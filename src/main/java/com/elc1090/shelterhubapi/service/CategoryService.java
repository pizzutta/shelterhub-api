package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.Category;
import com.elc1090.shelterhubapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category save(NameDTO data) {
        Category category = new Category();
        category.setName(data.name());

        return repository.save(category);
    }
}
