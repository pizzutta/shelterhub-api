package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.ShelterRegisterDTO;
import com.elc1090.shelterhubapi.model.Shelter;
import com.elc1090.shelterhubapi.service.ShelterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/shelter")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class ShelterController {

    @Autowired
    private ShelterService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<Shelter> shelters = service.findAll();
        return !shelters.isEmpty() ? ResponseEntity.ok(shelters) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        Shelter shelter = service.findById(id);
        return shelter != null ? ResponseEntity.ok(shelter) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ShelterRegisterDTO data) {
        Shelter shelter = service.save(data);
        return ResponseEntity.created(URI.create("/shelter/" + shelter.getId())).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid ShelterRegisterDTO data) {
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
