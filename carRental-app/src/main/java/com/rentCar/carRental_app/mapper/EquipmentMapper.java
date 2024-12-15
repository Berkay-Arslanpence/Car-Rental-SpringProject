package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.dto.EquipmentDTO;

import java.util.ArrayList;
import java.util.List;

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
    public static List<EquipmentDTO> EquipmentListToEquipmentDTOList(List<Equipment> list) {
        List<EquipmentDTO> equipmentDTOList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            equipmentDTOList.add(EquipmentToEquipmentDTO(list.get(i)));
        }
        return equipmentDTOList;
    }
    public static List<Equipment> EquipmentDTOListToEquipmentList(List<EquipmentDTO> list) {
        List<Equipment> equipmentList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            equipmentList.add(EquipmentDTOtoEquipment(list.get(i)));
        }
        return equipmentList;
    }

}
