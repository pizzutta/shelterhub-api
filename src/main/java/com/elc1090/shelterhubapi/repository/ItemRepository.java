package com.elc1090.shelterhubapi.repository;

import com.elc1090.shelterhubapi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
