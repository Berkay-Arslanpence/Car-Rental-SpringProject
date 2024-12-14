package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.repo.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rentCar.carRental_app.dto.EquipmentDTO;
import com.rentCar.carRental_app.mapper.EquipmentMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    EquipmentMapper equipmentMapper;
    EquipmentDTO equipmentDTO;
    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<EquipmentDTO> getAllEquipment() {
        List<Equipment> l=equipmentRepository.findAll();
        List<EquipmentDTO> m=new ArrayList<>();
        for(int i=0;i<l.size();i++){
            m.add(equipmentMapper.EquipmentToEquipmentDTO(l.get(i)));
        }
        return m;
    }

    public EquipmentDTO getEquipmentByName(String name) {
        Equipment e=equipmentRepository.findByName(name);
       return equipmentMapper.EquipmentToEquipmentDTO(e);
    }
}
