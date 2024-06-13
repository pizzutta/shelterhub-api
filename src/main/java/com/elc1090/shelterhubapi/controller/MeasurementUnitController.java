package com.elc1090.shelterhubapi.controller;

import com.elc1090.shelterhubapi.dto.IdDTO;
import com.elc1090.shelterhubapi.dto.NameDTO;
import com.elc1090.shelterhubapi.model.MeasurementUnit;
import com.elc1090.shelterhubapi.service.MeasurementUnitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/measurement-unit")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class MeasurementUnitController {

    @Autowired
    private MeasurementUnitService service;

    @GetMapping
    public ResponseEntity findAll() {
        List<MeasurementUnit> measurementUnits = service.findAll();
        return !measurementUnits.isEmpty() ? ResponseEntity.ok(measurementUnits) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable(value = "id") Long id) {
        MeasurementUnit measurementUnit = service.findById(id);
        return measurementUnit != null ? ResponseEntity.ok(measurementUnit) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid NameDTO data) {
        MeasurementUnit measurementUnit = service.save(data);
        return ResponseEntity.created(URI.create("/measurement-unit/" + measurementUnit.getId())).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody MeasurementUnit measurementUnit) {
        service.update(measurementUnit);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteById(@RequestBody IdDTO data) {
        service.deleteById(data.id());
        return ResponseEntity.ok().build();
    }
}
