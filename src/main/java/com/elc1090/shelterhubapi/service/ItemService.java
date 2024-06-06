package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ItemRegisterDTO;
import com.elc1090.shelterhubapi.model.Item;
import com.elc1090.shelterhubapi.repository.CategoryRepository;
import com.elc1090.shelterhubapi.repository.ItemRepository;
import com.elc1090.shelterhubapi.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MeasurementUnitRepository measurementUnitRepository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item save(ItemRegisterDTO data) {
        Item item = new Item();
        item.setName(data.name());
        item.setMeasurementUnit(measurementUnitRepository.findById(data.measurementUnitId()).get());
        item.setCategory(categoryRepository.findById(data.categoryId()).get());

        return repository.save(item);
    }
}
