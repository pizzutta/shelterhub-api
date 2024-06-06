package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
