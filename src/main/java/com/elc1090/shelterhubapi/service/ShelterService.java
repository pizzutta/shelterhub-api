package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.ShelterRegisterDTO;
import com.elc1090.shelterhubapi.model.Address;
import com.elc1090.shelterhubapi.model.Shelter;
import com.elc1090.shelterhubapi.repository.AddressRepository;
import com.elc1090.shelterhubapi.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {

    @Autowired
    private ShelterRepository repository;
    @Autowired
    private AddressRepository addressRepository;

    public List<Shelter> findAll() {
        return repository.findAll();
    }

    public Shelter findById(Long id) {
        Optional<Shelter> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public Shelter save(ShelterRegisterDTO data) {
        Address address = new Address();
        mountAddress(data, address);
        addressRepository.save(address);

        Shelter shelter = new Shelter();
        shelter.setName(data.name());
        shelter.setAddress(address);
        repository.save(shelter);

        return repository.save(shelter);
    }

    public void update(ShelterRegisterDTO data) {
        Shelter shelter = findById(data.id());
        shelter.setName(data.name());

        Address address = shelter.getAddress();
        mountAddress(data, address);
        addressRepository.save(address);

        repository.save(shelter);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private static void mountAddress(ShelterRegisterDTO data, Address address) {
        address.setStreet(data.address().street());
        address.setNumber(data.address().number());
        address.setComplement(data.address().complement());
        address.setDistrict(data.address().district());
        address.setCity(data.address().city());
        address.setState(data.address().state());
        address.setZipCode(data.address().zipCode());
    }
}
