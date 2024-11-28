package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Fetch a location by its unique code
    Location findByCode(String code);
}
