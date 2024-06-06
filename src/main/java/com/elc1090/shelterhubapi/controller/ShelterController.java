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

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ShelterRegisterDTO data) {
        Shelter shelter = service.save(data);
        return ResponseEntity.created(URI.create("/shelter/" + shelter.getId())).build();
    }
}
