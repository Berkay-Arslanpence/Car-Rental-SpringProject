package com.rentCar.carRental_app;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.service.CarService;
import com.rentCar.carRental_app.repo.CarRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.times;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    void testSearchAvailableCars() {
        // Create test data
        Car car1 = new Car();
        car1.setBarcode("CAR005");
        car1.setCarType("SUV");
        car1.setTransmissionType("Manual");
        car1.setStatus(Car.CarStatus.AVAILABLE);
        // Set other necessary fields

        Car car2 = new Car();
        car2.setBarcode("CAR007");
        car2.setCarType("SEDAN");
        car2.setTransmissionType("Automatic");
        car2.setStatus(Car.CarStatus.AVAILABLE);
        // Set other necessary fields

        // Mock the repository method
        when(carRepository.searchAvailableCars("SUV", "Automatic")).thenReturn(Arrays.asList(car1, car2));

        // Test the method
        List<CarDTO> result = carService.searchAvailableCars("SUV", "Automatic");
        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i).getBarcode());
            System.out.println(result.get(i).getCarType());
        }

        // Assertions
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getBarcode()).isEqualTo("CAR005");
        assertThat(result.get(1).getBarcode()).isEqualTo("CAR007");
    }

    @Test
    void testDeleteCarByBarcode_CarNotUsedInReservation() {
        // Mock the repository methods
        when(carRepository.isCarDeletable("CAR001")).thenReturn(false);

        // Test the method
        boolean result = carService.deleteCarByBarcode("CAR001");

        // Assertions
        assertThat(result).isTrue();
        verify(carRepository, times(1)).deleteCar("CAR001");
    }

    @Test
    void testDeleteCarByBarcode_CarUsedInReservation() {
        // Mock the repository methods
        when(carRepository.isCarDeletable("CAR001")).thenReturn(true);
        //System.out.println("USED IN RESERVATION");
        // Test the method
        boolean result = carService.deleteCarByBarcode("CAR001");

        // Assertions
        assertThat(result).isFalse();
        System.out.println("NOT USED IN RESERVATION");
        verify(carRepository, times(0)).deleteCar("CAR001");
        System.out.println("DELETED");
    }
}
