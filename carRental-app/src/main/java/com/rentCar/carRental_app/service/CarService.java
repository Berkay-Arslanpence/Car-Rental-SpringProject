package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.mapper.CarMapper;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
     CarRepository carRepository;

    public boolean deleteCarByBarcode(String barcode) {
        if(!carRepository.isCarUsedInReservation(barcode)){
            return false;
        }
        else{
            carRepository.deleteCar(barcode);
            return true;
        }
    }
}
