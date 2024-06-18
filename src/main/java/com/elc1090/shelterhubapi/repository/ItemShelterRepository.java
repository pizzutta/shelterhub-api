package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.ItemShelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ItemShelterRepository extends JpaRepository<ItemShelter, Long>, JpaSpecificationExecutor<ItemShelter> {
}
