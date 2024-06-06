package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.MeasurementUnit;
import com.elc1090.shelterhubapi.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementUnitService {

    @Autowired
    private MeasurementUnitRepository repository;

    public List<MeasurementUnit> findAll() {
        return repository.findAll();
    }

    public MeasurementUnit save(NameDTO data) {
        MeasurementUnit item = new MeasurementUnit();
        item.setName(data.name());

        return repository.save(item);
    }
}
