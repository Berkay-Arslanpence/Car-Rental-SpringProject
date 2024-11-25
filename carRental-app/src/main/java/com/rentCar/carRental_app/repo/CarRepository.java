package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // Custom query to find cars by status
    List<Car> findAllByStatus(Car.CarStatus status);
}