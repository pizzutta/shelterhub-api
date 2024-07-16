package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ShelterTransactionRegisterDTO;
import com.elc1090.shelterhubapi.dto.TransactionRegisterDTO;
import com.elc1090.shelterhubapi.model.ItemShelter;
import com.elc1090.shelterhubapi.model.ShelterTransaction;
import com.elc1090.shelterhubapi.repository.ItemRepository;
import com.elc1090.shelterhubapi.repository.ItemShelterRepository;
import com.elc1090.shelterhubapi.repository.ShelterRepository;
import com.elc1090.shelterhubapi.repository.ShelterTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.elc1090.shelterhubapi.model.ActionsEnum.INPUT;
import static com.elc1090.shelterhubapi.model.ActionsEnum.OUTPUT;
import static java.time.LocalDateTime.now;

@Service
public class ShelterTransactionService {

    @Autowired
    private ShelterTransactionRepository repository;
    @Autowired
    private ItemShelterRepository itemShelterRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private TransactionService transactionService;

    public List<ShelterTransaction> findAll() {
        return repository.findAll();
    }

    public ShelterTransaction findById(Long id) {
        Optional<ShelterTransaction> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public ShelterTransaction makeShelterTransaction(ShelterTransactionRegisterDTO data) {
        ItemShelter giverItemShelter = itemShelterRepository.findByItem_IdAndShelterId(data.itemId(), data.giverShelterId());
        ItemShelter receiverItemShelter = itemShelterRepository.findByItem_IdAndShelterId(data.itemId(), data.receiverShelterId());

        if (giverItemShelter != null && receiverItemShelter != null) {
            int quantity = data.quantity();
            transactionService.makeTransaction(new TransactionRegisterDTO(null, OUTPUT, quantity, giverItemShelter.getId()), true);
            transactionService.makeTransaction(new TransactionRegisterDTO(null, INPUT, quantity, receiverItemShelter.getId()), true);
            return this.save(data);
        } else {
            return null;
        }
    }

    private ShelterTransaction save(ShelterTransactionRegisterDTO data) {
        ShelterTransaction shelterTransaction = new ShelterTransaction();
        shelterTransaction.setDate(now());
        shelterTransaction.setQuantity(data.quantity());
        shelterTransaction.setItem(itemRepository.getReferenceById(data.itemId()));
        shelterTransaction.setGiverShelter(shelterRepository.getReferenceById(data.giverShelterId()));
        shelterTransaction.setReceiverShelter(shelterRepository.getReferenceById(data.receiverShelterId()));

        return repository.save(shelterTransaction);
    }
}
