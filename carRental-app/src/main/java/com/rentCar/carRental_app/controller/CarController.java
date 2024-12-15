package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.repo.CarRepository;
import com.rentCar.carRental_app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> searchAvailableCars(@RequestParam String carType, @RequestParam String transmissionType) {
        List<CarDTO> availableCars = carService.searchAvailableCars(carType, transmissionType);
        if (availableCars.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(availableCars);
    }

    @DeleteMapping("/{barcode}")
    public ResponseEntity<Void> deleteCar(@PathVariable String barcode) {
        try {
            if (!carRepository.existsByBarcode(barcode)) {
                return ResponseEntity.notFound().build();
            }
            boolean isDeleted = carService.deleteCarByBarcode(barcode);
            if (isDeleted) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(406).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
