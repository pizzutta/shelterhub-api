package com.elc1090.shelterhubapi.controller;

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
    public ResponseEntity findAll() {
        List<Transaction> transactions = service.findAll();
        return !transactions.isEmpty() ? ResponseEntity.ok(transactions) : ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity makeTransaction(@RequestBody @Valid TransactionRegisterDTO data) {
        Transaction transaction = service.makeTransaction(data);
        return ResponseEntity.created(URI.create("/transaction/" + transaction.getId())).build();
    }
}
