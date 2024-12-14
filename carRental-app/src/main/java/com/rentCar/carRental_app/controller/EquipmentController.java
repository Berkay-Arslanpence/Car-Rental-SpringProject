package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.rentCar.carRental_app.dto.EquipmentDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<EquipmentDTO>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }

    @GetMapping("/{name}")
    public ResponseEntity<EquipmentDTO> getEquipmentByName(@PathVariable String name) {
        EquipmentDTO equipment = equipmentService.getEquipmentByName(name);
        if (equipment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipment);
    }
}
