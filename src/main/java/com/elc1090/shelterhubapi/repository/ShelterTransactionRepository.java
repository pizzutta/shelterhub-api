package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.ShelterTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterTransactionRepository extends JpaRepository<ShelterTransaction, Long> {
}
