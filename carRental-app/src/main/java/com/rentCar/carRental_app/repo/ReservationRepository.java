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


    @Modifying
    @Query(value= "insert into reservation r   )
    Reservation InsertReservation();
    Reservation findByReservationNumber(String reservationNumber);


}


