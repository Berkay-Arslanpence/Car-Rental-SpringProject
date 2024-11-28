package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.repo.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentByName(String name) {
        return equipmentRepository.findByName(name);
    }
}
