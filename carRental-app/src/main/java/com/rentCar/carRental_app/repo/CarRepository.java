package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    @Query(value="select c from Car c where c.status='LOANED' or c.status='RESERVED'")
    List<Car> getAllRentedCars();

    @Query(value="select not exists(select 1 from Reservation r where r.car.barcode= :barcode and exists( select 1 from Car c where c.barcode= :barcode and c.status='AVAILABLE'))")
    boolean isCarDeletable(String barcode);

    @Query(value="delete from Car c where c.barcode= :barcode")
    int deleteCar(String barcode);

    @Query(value="select c from Car c where c.barcode= :barcode")
    Car findCarByBarcode(String barcode);

    @Query(value="select exists(select 1  from Car c where c.barcode= :barcode and c.status='AVAILABLE')")
    boolean isAvailable(String barcode);


    void deleteByBarcodeAndStatus(String barcode, Car.CarStatus status);

    @Query("select c from Car c where c.status='AVAILABLE' and c.carType= :carType and c.transmissionType= :transmissionType ")
    List<Car> searchAvailableCars(String carType, String transmissionType);

    @Transactional
    @Modifying
    @Query("update Car c set c.status = com.rentCar.carRental_app.model.Car.CarStatus.AVAILABLE where c.barcode = :barcode")
    int updateCarStatusToAvailable(String barcode);
}
