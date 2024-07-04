package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.TransactionRegisterDTO;
import com.elc1090.shelterhubapi.model.ActionsEnum;
import com.elc1090.shelterhubapi.model.ItemShelter;
import com.elc1090.shelterhubapi.model.Transaction;
import com.elc1090.shelterhubapi.model.User;
import com.elc1090.shelterhubapi.repository.ItemShelterRepository;
import com.elc1090.shelterhubapi.repository.TransactionRepository;
import jakarta.persistence.criteria.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.elc1090.shelterhubapi.model.ActionsEnum.valueOf;
import static java.time.LocalDateTime.now;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private ItemShelterRepository itemShelterRepository;

    public List<Transaction> findAll() {
        return repository.findAll();
    }

    public Transaction findById(Long id) {
        Optional<Transaction> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public List<Transaction> findByShelterId(Long shelterId) {
        Specification<Transaction> specification = (root, query, criteriaBuilder) -> {
            Join<ItemShelter, Transaction> join = root.join("itemShelter");
            return criteriaBuilder.equal(join.get("shelterId"), shelterId);
        };
        return repository.findAll(specification);
    }

    public Transaction makeTransaction(TransactionRegisterDTO data) {
        Optional<ItemShelter> optional = itemShelterRepository.findById(data.itemShelterId());

        if (optional.isPresent()) {
            ItemShelter itemShelter = optional.get();
            ActionsEnum action = valueOf(data.action());
            int quantity = itemShelter.getQuantity();

            if (action.equals(ActionsEnum.INPUT)) {
                quantity += data.quantity();
            } else {
                quantity -= data.quantity();
            }

            itemShelter.setQuantity(quantity);
            itemShelterRepository.save(itemShelter);
            return this.save(data, itemShelter);
        } else {
            return null;
        }
    }

    private Transaction save(TransactionRegisterDTO data, ItemShelter itemShelter) {
        User user = (User) getContext().getAuthentication().getPrincipal();

        Transaction transaction = new Transaction();
        transaction.setDate(now());
        transaction.setAction(valueOf(data.action()));
        transaction.setQuantity(data.quantity());
        transaction.setItemShelter(itemShelter);
        transaction.setUser(user);

        return repository.save(transaction);
    }

    public void update(TransactionRegisterDTO data) {
        Transaction transaction = findById(data.id());
        transaction.setAction(valueOf(data.action()));
        transaction.setQuantity(data.quantity());
        repository.save(transaction);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
