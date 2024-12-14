package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.mapper.CarMapper;
import com.rentCar.carRental_app.model.Car;
import com.rentCar.carRental_app.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> searchAvailableCars(String carType, String transmissionType) {
        List<Car> carList = carRepository.searchAvailableCars(carType, transmissionType);
        List<CarDTO> carDTOList = new ArrayList<>();
        for (Car car : carList) {
            carDTOList.add(CarMapper.CarToCarDTO(car));
        }
        return carDTOList;
    }

    public boolean deleteCarByBarcode(String barcode) {
        if (!carRepository.isCarDeletable(barcode)) {
            return false;
        }
        carRepository.deleteCar(barcode);
        return true;
    }
}
