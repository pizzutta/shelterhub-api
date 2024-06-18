package com.elc1090.shelterhubapi.service;

import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.MeasurementUnit;
import com.elc1090.shelterhubapi.repository.MeasurementUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementUnitService {

    @Autowired
    private MeasurementUnitRepository repository;

    public List<MeasurementUnit> findAll() {
        return repository.findAll();
    }

    public MeasurementUnit findById(Long id) {
        Optional<MeasurementUnit> optional = repository.findById(id);
        return optional.orElse(null);
    }

    public MeasurementUnit save(NameDTO data) {
        MeasurementUnit measurementUnit = new MeasurementUnit();
        measurementUnit.setName(data.name());

        return repository.save(measurementUnit);
    }

    public void update(NameDTO data) {
        MeasurementUnit measurementUnit = findById(data.id());
        measurementUnit.setName(data.name());
        repository.save(measurementUnit);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
