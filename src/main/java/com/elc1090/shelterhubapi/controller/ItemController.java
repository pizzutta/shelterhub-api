package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.ItemRegisterDTO;
import com.elc1090.shelterhubapi.model.Item;
import com.elc1090.shelterhubapi.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<Item> items = service.findAll();
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        Item item = service.findById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ItemRegisterDTO data) {
        Item item = service.save(data);
        return ResponseEntity.created(URI.create("/item/" + item.getId())).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid ItemRegisterDTO data) {
        if (data.id() == null) {
            return ResponseEntity.badRequest().build();
        }

        service.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
