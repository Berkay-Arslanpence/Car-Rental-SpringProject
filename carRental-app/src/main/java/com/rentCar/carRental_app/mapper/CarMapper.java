package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.model.Car;

public class CarMapper {
    public static Car CarDTOToCar(CarDTO carDTO) {
        Car car = new Car();
        car.setLicensePlate(carDTO.getLicensePlate());
        car.setBarcode(carDTO.getBarcode());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setTransmissionType(carDTO.getTransmissionType());
        car.setCarType(carDTO.getCarType());
        car.setPassengerCapacity(carDTO.getPassengerCapacity());
        car.setDailyPrice(carDTO.getDailyPrice());
        car.setMileage(carDTO.getMileage());
        car.setStatus(carDTO.getStatus());
        return car;
    }
    public static CarDTO CarToCarDTO(Car car) {
        return new CarDTO(car.getLicensePlate(), car.getBarcode(), car.getBrand(), car.getModel(), car.getTransmissionType(), car.getCarType(), car.getPassengerCapacity(), car.getDailyPrice(), car.getMileage(), car.getStatus());
    }
}
