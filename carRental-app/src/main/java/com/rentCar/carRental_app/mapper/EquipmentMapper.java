package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.dto.EquipmentDTO;

public class EquipmentMapper {
    public static Equipment EquipmentDTOtoEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.getName());
        equipment.setPrice(equipmentDTO.getPrice());
        return equipment;
    }
    public static EquipmentDTO EquipmentToEquipmentDTO(Equipment equipment) {
        return new EquipmentDTO(equipment.getName(), equipment.getPrice());
    }
}
