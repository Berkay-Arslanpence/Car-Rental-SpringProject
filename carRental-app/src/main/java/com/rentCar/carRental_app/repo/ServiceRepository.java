package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Custom query to find a service by name
    Service findByName(String name);
}