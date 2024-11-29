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

    List<Car> findByCarTypeAndTransmissionTypeAndStatus(String carType, String transmissionType, Car.CarStatus status);


    @Query(value="select c from Car c where c.status='LOANED' or c.status='RESERVED'")
    List<Car> getAllRentedCars();

    @Query(value="select not exists(select 1 from Reservation r where r.car.barcode=? and exists( select 1 from Car c where c.barcode=? and c.status='AVAILABLE'))")
    boolean isCarUsedInReservation(String barcode);

    @Modifying
    @Query(value="delete from Car c where c.barcode=?")
    int deleteCar(String barcode);


    Car findById(long id);

    void deleteByBarcodeAndStatus(String barcode, Car.CarStatus status);

    @Query("select c from Car c where c.status='AVAILABLE' and c.carType=?1 and c.transmissionType=?2 ")
    List<Car> searchAvailableCars(String carType, String transmissionType);

    @Transactional
    @Modifying
    @Query("update Car c set c.status = com.rentCar.carRental_app.model.Car.CarStatus.AVAILABLE where c.barcode = ?1")
    int updateCarStatusToAvailable(String barcode);
}
