package com.rentCar.carRental_app;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.service.*;
import com.rentCar.carRental_app.repo.CarRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class CarTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ReservationService reservationService;


    @Test
    void testSearchAvailableCars() {
        List<CarDTO> result = carService.searchAvailableCars("Economy", "Automatic");
        for (CarDTO car : result) {
            assertThat(car.getStatus()).isEqualTo(Car.CarStatus.AVAILABLE);
            assertThat(car.getCarType()).isEqualTo("Economy");
            assertThat(car.getTransmissionType()).isEqualTo("Automatic");
        }
    }


    @Test
    void testDeleteCarByBarcode_CarNotUsedInReservation() {
        String barcode="CAR002";
        boolean a=carService.deleteCarByBarcode(barcode);
        if(a==true){
            assertTrue("Car with barcode "+ barcode + " should have been deleted or it can not be deleted if it couldn't pass",carRepository.findCarByBarcode(barcode) == null);
        }
        else{
            System.out.println("The car with barcode "+ barcode + " can not be deleted." );
        }
    }
}
