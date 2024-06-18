package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.IdDTO;
import com.elc1090.shelterhubapi.dto.ItemShelterRegisterDTO;
import com.elc1090.shelterhubapi.model.ItemShelter;
import com.elc1090.shelterhubapi.service.ItemShelterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/item-shelter")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class ItemShelterController {

    @Autowired
    private ItemShelterService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<ItemShelter> itemsShelter = service.findAll();
        return !itemsShelter.isEmpty() ? ResponseEntity.ok(itemsShelter) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        ItemShelter itemShelter = service.findById(id);
        return itemShelter != null ? ResponseEntity.ok(itemShelter) : ResponseEntity.notFound().build();
    }

    @GetMapping("/shelter/{shelter_id}")
    public ResponseEntity findByShelterId(@PathVariable(value = "shelter_id") Long shelterId) {
        List<ItemShelter> itemShelter = service.findByShelterId(shelterId);
        return !itemShelter.isEmpty() ? ResponseEntity.ok(itemShelter) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ItemShelterRegisterDTO data) {
        ItemShelter itemShelter = service.save(data);
        return ResponseEntity.created(URI.create("/item-shelter/" + itemShelter.getId())).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ItemShelter itemShelter) {
        service.update(itemShelter);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.ok().build();
    }
}
