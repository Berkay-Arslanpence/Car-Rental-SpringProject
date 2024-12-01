package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    @Query(value="select e from Equipment e where e.name=:name" )
    Equipment findByName(String name);
    @Query(value="select e from Equipment e where e.id=:id" )
    Equipment findById(long id);
}
