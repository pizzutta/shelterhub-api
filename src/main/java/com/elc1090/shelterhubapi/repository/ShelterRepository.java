package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
