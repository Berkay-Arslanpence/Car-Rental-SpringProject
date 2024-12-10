package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.dto.RentedCarDTO;
import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> makeReservation(@RequestParam String carBarcode,
                                                          @RequestParam int dayCount,
                                                          @RequestParam Long memberId,
                                                          @RequestParam String pickupLocationCode,
                                                          @RequestParam String dropOffLocationCode,
                                                          @RequestParam List<Services> addiServices,
                                                          @RequestParam List<Equipment> addiEquipments) {
        ReservationDTO reservation = reservationService.makeReservation(
                carBarcode, dayCount, memberId, pickupLocationCode, dropOffLocationCode,
                addiEquipments,addiServices);


        if (reservation == null) {
            return ResponseEntity.status(406).build(); // Not Acceptable
        }
        System.out.println("MERHABALAAAAAAAAAAAAAAAAAAAAARR");
        return ResponseEntity.ok(reservation);
    }

    @GetMapping("/rented")
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> rentedCars = reservationService.getAllRentedCars();
        if (rentedCars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rentedCars);
    }

    @PutMapping("/{reservationNumber}/return")
    public ResponseEntity<Void> returnCar(@PathVariable String reservationNumber) {
        boolean isReturned = reservationService.returnCar(reservationNumber);
        if (isReturned) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build(); // Not Found
        }
    }
}
