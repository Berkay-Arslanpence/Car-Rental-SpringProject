package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    // Find all reservations by status
    List<Reservation> findAllByStatus(Reservation.Status status);

    // Find reservations for a specific member
    List<Reservation> findAllByMemberId(Long memberId);
}

