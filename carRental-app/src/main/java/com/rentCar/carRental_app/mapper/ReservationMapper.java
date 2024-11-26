package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.model.Reservation;
import com.rentCar.carRental_app.dto.ReservationDTO;

public class ReservationMapper {
    public static Reservation ReservationDTOToReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setCreationDate(reservationDTO.getCreationDate());
        reservation.setPickUpDateTime(reservationDTO.getPickUpDateTime());
        reservation.setDropOffDateTime(reservationDTO.getDropOffDateTime());
        reservation.setReturnDate(reservationDTO.getReturnDate());
        return reservation;
    }
    public static ReservationDTO ReservationToReservationDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getCreationDate(),reservation.getPickUpDateTime(),reservation.getDropOffDateTime(),reservation.getReturnDate());
    }
}
