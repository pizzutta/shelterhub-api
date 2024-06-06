package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.Category;
import com.elc1090.shelterhubapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<Category> categories = service.findAll();
        return !categories.isEmpty() ? ResponseEntity.ok(categories) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid NameDTO data) {
        Category category = service.save(data);
        return ResponseEntity.created(URI.create("/category/" + category.getId())).build();
    }
}
