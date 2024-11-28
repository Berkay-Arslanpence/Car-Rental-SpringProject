package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    // Find reservations by status
    List<Reservation> findByStatus(Reservation.Status status);

    // Find a reservation by reservation number
    Reservation findByReservationNumber(String reservationNumber);

    // Fetch all rented (Loaned/Reserved) cars with reservation details
    List<Reservation> findAllByStatusIn(List<Reservation.Status> statuses);
}


