package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ItemRegisterDTO;
import com.elc1090.shelterhubapi.model.Item;
import com.elc1090.shelterhubapi.repository.CategoryRepository;
import com.elc1090.shelterhubapi.repository.ItemRepository;
import com.elc1090.shelterhubapi.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Item findById(Long id) {
        Optional<Item> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public Item save(ItemRegisterDTO data) {
        Item item = new Item();
        mountObject(data, item);

        return repository.save(item);
    }

    public void update(ItemRegisterDTO data) {
        Item item = findById(data.id());
        mountObject(data, item);
        repository.save(item);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void mountObject(ItemRegisterDTO data, Item item) {
        item.setName(data.name());
        item.setMeasurementUnit(measurementUnitRepository.getReferenceById(data.measurementUnitId()));
        item.setCategory(categoryRepository.getReferenceById(data.categoryId()));
    }
}
