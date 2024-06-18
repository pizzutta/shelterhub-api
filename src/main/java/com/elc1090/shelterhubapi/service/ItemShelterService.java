package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ItemShelterRegisterDTO;
import com.elc1090.shelterhubapi.model.ItemShelter;
import com.elc1090.shelterhubapi.model.Shelter;
import com.elc1090.shelterhubapi.repository.ItemRepository;
import com.elc1090.shelterhubapi.repository.ItemShelterRepository;
import com.elc1090.shelterhubapi.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemShelterService {

    @Autowired
    private ItemShelterRepository repository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShelterRepository shelterRepository;

    public List<ItemShelter> findAll() {
        return repository.findAll();
    }

    public ItemShelter findById(Long id) {
        Optional<ItemShelter> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public List<ItemShelter> findByShelterId(Long shelterId) {
        Specification<ItemShelter> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shelterId"), shelterId);
        return repository.findAll(specification);
    }

    public ItemShelter save(ItemShelterRegisterDTO data) {
        ItemShelter itemShelter = new ItemShelter();
        itemShelter.setItem(itemRepository.findById(data.itemId()).get());
        itemShelter.setQuantity(data.quantity());
        repository.save(itemShelter);

        Shelter shelter = shelterRepository.findById(data.shelterId()).get();
        shelter.getItems().add(itemShelter);
        shelterRepository.save(shelter);

        return itemShelter;
    }

    public void update(ItemShelter itemShelter) {
        repository.save(itemShelter);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
