package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.dto.MakeReservationRequestDTO;
import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.dto.RentedCarDTO;
import com.rentCar.carRental_app.repo.LocationRepository;
import com.rentCar.carRental_app.service.LocationService;
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
    public ResponseEntity<ReservationDTO> makeReservation(@RequestBody MakeReservationRequestDTO request) {
        ReservationDTO reservation = reservationService.makeReservation(
                request.getCarBarcode(),
                request.getDayCount(),
                request.getMemberId(),
                request.getPickupLocationCode(),
                request.getDropOffLocationCode(),
                request.getAddiEquipments(),
                request.getAddiServices()
        );

        if (reservation == null) {
            return ResponseEntity.status(406).build(); // Not Acceptable
        }
        return ResponseEntity.ok(reservation);
    }

    /*
    @PostMapping
    public ResponseEntity<ReservationDTO> makeReservation(@RequestParam String carBarcode,
                                                          @RequestParam int dayCount,
                                                          @RequestParam Long memberId,
                                                          @RequestParam String pickupLocationCode,
                                                          @RequestParam String dropOffLocationCode,
                                                          @RequestBody List<Services> addiServices,
                                                          @RequestBody List<Equipment> addiEquipments) {
        ReservationDTO reservation = reservationService.makeReservation(
                carBarcode, dayCount, memberId, pickupLocationCode, dropOffLocationCode,
                addiEquipments, addiServices);

        if (reservation == null) {
            return ResponseEntity.status(406).build(); // Not Acceptable
        }
        return ResponseEntity.ok(reservation);
    }
     */


    @GetMapping("/rentedCars")
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> rentedCars = reservationService.getAllRentedCars();
        if (rentedCars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rentedCars);
    }

    @PutMapping("/{reservationNumber}/returnCar")
    public ResponseEntity<Void> returnCar(@PathVariable String reservationNumber) {
         try {
             boolean isReturned = reservationService.returnCar(reservationNumber);
             if (isReturned) {
                 return ResponseEntity.ok().build();
             } else {
                 return ResponseEntity.notFound().build(); // Not Found
             }
         } catch (Exception e) {
             return ResponseEntity.status(500).build();
         }
    }

    @DeleteMapping("/{reservationNumber}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String reservationNumber) {
        try {
            boolean isDeleted = reservationService.deleteReservationByReservationNumber(reservationNumber);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{reservationNumber}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable String reservationNumber) {
         try {
             boolean isCancelled = reservationService.CancelReservation(reservationNumber);
             if (isCancelled) {
                 return ResponseEntity.ok().build();
             } else {
                 return ResponseEntity.notFound().build(); // Not Found
             }
         } catch (Exception e) {
             return ResponseEntity.status(500).build();
         }
    }

    @PutMapping("/{reservationNumber}/addService/{serviceId}")
    public ResponseEntity<Void> addAdditionalService(@PathVariable String reservationNumber, @PathVariable long serviceId) {
        try {
            boolean isAdded = reservationService.AddAdditionalService(reservationNumber, serviceId);
            if (isAdded) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{reservationNumber}/addEquipment/{equipmentId}")
    public ResponseEntity<Void> addAdditionalEquipment(@PathVariable String reservationNumber, @PathVariable long equipmentId) {
        try {
            boolean isAdded = reservationService.AddAdditionalEquipment(reservationNumber, equipmentId);
            if (isAdded) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build(); // Not Found
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
