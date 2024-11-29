package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    // Fetch a location by its unique code
    @Query(value="select l from Location l where l.code=?")
    Location findByCode(String code);
}
