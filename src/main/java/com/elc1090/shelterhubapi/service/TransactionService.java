package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.TransactionRegisterDTO;
import com.elc1090.shelterhubapi.model.ActionsEnum;
import com.elc1090.shelterhubapi.model.ItemShelter;
import com.elc1090.shelterhubapi.model.Transaction;
import com.elc1090.shelterhubapi.repository.ItemShelterRepository;
import com.elc1090.shelterhubapi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.elc1090.shelterhubapi.model.ActionsEnum.valueOf;
import static java.time.LocalDateTime.now;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Autowired
    private ItemShelterRepository itemShelterRepository;

    public List<Transaction> findAll() {
        return repository.findAll();
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
        Transaction transaction = new Transaction();
        transaction.setDate(now());
        transaction.setAction(valueOf(data.action()));
        transaction.setQuantity(data.quantity());
        transaction.setItemShelter(itemShelter);

        return repository.save(transaction);
    }
}
