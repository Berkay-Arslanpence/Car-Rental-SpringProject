package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Equipment> getEquipmentByName(@PathVariable String name) {
        Equipment equipment = equipmentService.getEquipmentByName(name);
        if (equipment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipment);
    }
}
