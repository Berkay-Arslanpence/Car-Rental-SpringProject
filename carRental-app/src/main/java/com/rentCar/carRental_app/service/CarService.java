package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.mapper.CarMapper;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.repo.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarDTO> searchAvailableCars(String carType, String transmissionType) {
        List<Car> availableCars = carRepository.findByCarTypeAndTransmissionTypeAndStatus(
                carType, transmissionType, Car.CarStatus.AVAILABLE
        );
        return carMapper.toDTOList(availableCars);
    }

    public CarDTO getCarByBarcode(String barcode) {
        Car car = carRepository.findByBarcode(barcode);
        if (car == null) {
            throw new IllegalArgumentException("Car with barcode " + barcode + " not found");
        }
        return carMapper.CarToCarDTO(car);
    }

    public void updateCarStatus(String barcode, Car.CarStatus status) {
        Car car = carRepository.findByBarcode(barcode);
        if (car == null) {
            throw new IllegalArgumentException("Car with barcode " + barcode + " not found");
        }
        car.setStatus(status);
        carRepository.save(car);
    }
}
