package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findByCarTypeAndTransmissionTypeAndStatus(String carType, String transmissionType, Car.CarStatus status);


    Car findByBarcode(String barcode);


    void deleteByBarcodeAndStatus(String barcode, Car.CarStatus status);
}
