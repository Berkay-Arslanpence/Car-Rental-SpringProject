package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.mapper.CarMapper;
import com.rentCar.carRental_app.mapper.ReservationMapper;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.model.Reservation;
import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.repo.CarRepository;
import com.rentCar.carRental_app.repo.LocationRepository;
import com.rentCar.carRental_app.repo.MemberRepository;
import com.rentCar.carRental_app.repo.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
    }

    public boolean returnCar(String reservationNumber){
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);

        if(reservation == null){
            return false;
        }

        Car car = reservation.getCar();
        if(car == null){
            return false;
        }

        try{
            reservation.setStatus(Reservation.Status.COMPLETED);
            reservation.setReturnDate(Date.from(Instant.from(LocalDateTime.now())));
            reservationRepository.save(reservation);

            carRepository.updateCarStatusToAvailable(car.getBarcode());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Reservation makeReservation(String carBarcode, int dayCount, Long memId, String pickUpLocCode, String dropOffLocCode, List<Equipment> addiEquipments, List<Services>addiServices){
        if(carRepository.isAvailable(carBarcode)){
            Reservation reservation=new Reservation();
            reservation.setCar(carRepository.findCarByBarcode(carBarcode));
            reservation.setCreationDate(Date.from(Instant.from(LocalDateTime.now())));
            reservation.setMember(memberRepository.findMemById(memId));
            reservation.setPickUpLocation(locationRepository.findByCode(pickUpLocCode));
            reservation.setDropOffLocation(locationRepository.findByCode(dropOffLocCode));
            reservation.setServiceList(addiServices);
            reservation.setEquipmentList(addiEquipments);
            return reservation;
        }
        else{
            System.out.println("Reservation Failed!,The car is not available");
        }

        return null;
    }


}
