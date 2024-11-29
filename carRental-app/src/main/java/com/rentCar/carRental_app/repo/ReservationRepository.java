package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {


    Reservation findByReservationNumber(String reservationNumber);

    @Query("SELECT r FROM Reservation r " +
            "JOIN r.car c " +
            "JOIN r.member m " +
            "WHERE c.status = com.rentCar.carRental_app.model.Car.CarStatus.LOANED " +
            "OR c.status = com.rentCar.carRental_app.model.Car.CarStatus.RESERVED")
    List<Reservation> findAllReservationsWithLoanedOrReservedCars();

    @Query(value="delete from Reservation r where r.reservationNumber= :reservationNumber")
    int deleteReservation(String reservationNumber);

}


