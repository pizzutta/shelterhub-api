package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
