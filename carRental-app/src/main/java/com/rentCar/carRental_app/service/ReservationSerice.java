package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.mapper.ReservationMapper;
import com.rentCar.carRental_app.model.Reservation;
import com.rentCar.carRental_app.repo.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.ReservationToReservationDTO(reservations);
    }

    public ReservationDTO getReservationByNumber(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found with number " + reservationNumber);
        }
        return reservationMapper.toDTO(reservation);
    }

    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        reservation.setCreationDate(LocalDateTime.now());
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDTO(savedReservation);
    }

    public void cancelReservation(String reservationNumber) {
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found with number " + reservationNumber);
        }
        reservation.setStatus(Reservation.Status.CANCELLED);
        reservationRepository.save(reservation);
    }
}
