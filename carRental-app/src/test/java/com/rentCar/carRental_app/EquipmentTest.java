package com.rentCar.carRental_app;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.service.EquipmentService;
import com.rentCar.carRental_app.repo.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EquipmentTest {

    @InjectMocks
    private EquipmentService equipmentService;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Test
    void testGetAllEquipment() {
        Equipment equipment = new Equipment();
        equipment.setName("GPS");
        when(equipmentRepository.findAll()).thenReturn(Arrays.asList(equipment));

        List<Equipment> result = equipmentService.getAllEquipment();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("GPS");
        verify(equipmentRepository, times(1)).findAll();
    }

    @Test
    void testGetEquipmentByName() {
        Equipment equipment = new Equipment();
        equipment.setName("GPS");
        when(equipmentRepository.findByName("GPS")).thenReturn(equipment);

        Equipment result = equipmentService.getEquipmentByName("GPS");

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("GPS");
        verify(equipmentRepository, times(1)).findByName("GPS");
    }
}
