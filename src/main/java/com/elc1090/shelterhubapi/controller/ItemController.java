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

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ItemRegisterDTO data) {
        Item item = service.save(data);
        return ResponseEntity.created(URI.create("/item/" + item.getId())).build();
    }
}
