package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    List<Car> findByCarTypeAndTransmissionTypeAndStatus(String carType, String transmissionType, Car.CarStatus status);


    @Query(value="select c from Car c where c.status='LOANED' or c.status='RESERVED'")
    List<Car> getAllRentedCars();

    @Modifying
    @Query(value="delete from Car c where (c.status!='LOANED' or c.status!='RESERVED')and c.barcode=?")
    List<Car> deleteCar(String barcode);



    void deleteByBarcodeAndStatus(String barcode, Car.CarStatus status);
}
