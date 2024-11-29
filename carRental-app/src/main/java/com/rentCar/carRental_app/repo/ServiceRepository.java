package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Long> {

    Services findByName(String name);
    Services findById(long id);
}
