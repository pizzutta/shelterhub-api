package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.Category;
import com.elc1090.shelterhubapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public Category save(NameDTO data) {
        Category category = new Category();
        category.setName(data.name());

        return repository.save(category);
    }

    public void update(Category category){
        repository.save(category);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
