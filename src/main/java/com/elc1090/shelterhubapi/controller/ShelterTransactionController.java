package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.ShelterTransactionRegisterDTO;
import com.elc1090.shelterhubapi.model.ShelterTransaction;
import com.elc1090.shelterhubapi.service.ShelterTransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/shelter-transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class ShelterTransactionController {

    @Autowired
    private ShelterTransactionService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<ShelterTransaction> transactions = service.findAll();
        return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        ShelterTransaction shelterTransaction = service.findById(id);
        return shelterTransaction != null ? ResponseEntity.ok(shelterTransaction) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity makeShelterTransaction(@RequestBody @Valid ShelterTransactionRegisterDTO data) {
        ShelterTransaction shelterTransaction = service.makeShelterTransaction(data);
        return ResponseEntity.created(URI.create("/shelter-transaction/" + shelterTransaction.getId())).build();
    }
}
