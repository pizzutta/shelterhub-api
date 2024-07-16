package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.TransactionFilter;
import com.elc1090.shelterhubapi.dto.TransactionRegisterDTO;
import com.elc1090.shelterhubapi.model.Transaction;
import com.elc1090.shelterhubapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @GetMapping
    public ResponseEntity findAll(TransactionFilter filter) {
        List<Transaction> transactions = service.findAll(filter);
        return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        Transaction transaction = service.findById(id);
        return transaction != null ? ResponseEntity.ok(transaction) : ResponseEntity.notFound().build();
    }

    @GetMapping("/shelter/{shelter_id}")
    public ResponseEntity findByShelterId(@PathVariable(value = "shelter_id") Long shelterId) {
        List<Transaction> transactions = service.findByShelterId(shelterId);
        return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity makeTransaction(@RequestBody @Valid TransactionRegisterDTO data) {
        Transaction transaction = service.makeTransaction(data, false);
        return ResponseEntity.created(URI.create("/transaction/" + transaction.getId())).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid TransactionRegisterDTO data) {
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
