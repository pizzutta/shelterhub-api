package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ShelterRegisterDTO;
import com.elc1090.shelterhubapi.model.Address;
import com.elc1090.shelterhubapi.model.Shelter;
import com.elc1090.shelterhubapi.repository.AddressRepository;
import com.elc1090.shelterhubapi.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository repository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Shelter> findAll() {
        return repository.findAll();
    }

    public Shelter save(ShelterRegisterDTO data) {
        Address address = new Address();
        address.setStreet(data.address().street());
        address.setNumber(data.address().number());
        address.setComplement(data.address().complement());
        address.setDistrict(data.address().district());
        address.setCity(data.address().city());
        address.setState(data.address().state());
        address.setZipCode(data.address().zipCode());
        addressRepository.save(address);

        Shelter shelter = new Shelter();
        shelter.setName(data.name());
        shelter.setAddress(address);
        repository.save(shelter);

        return repository.save(shelter);
    }
}
