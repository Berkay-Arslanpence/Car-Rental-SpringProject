package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Find equipment by name
    Equipment findByName(String name);
}
