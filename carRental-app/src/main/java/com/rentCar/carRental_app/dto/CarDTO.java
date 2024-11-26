package com.rentCar.carRental_app.dto;

import com.rentCar.carRental_app.model.Car;

public class CarDTO {
    private String licensePlate;
    private String barcode;
    private String brand;
    private String model;
    private String transmissionType;
    private String carType;
    private int passengerCapacity;
    private double dailyPrice;
    private double mileage;
    private Car.CarStatus status;

    public CarDTO() {
        super();
    }
    public CarDTO(String licensePlate, String barcode, String brand, String model, String transmissionType,String carType, int passengerCapacity, double dailyPrice, double mileage, Car.CarStatus status) {
        super();
        this.licensePlate = licensePlate;
        this.barcode = barcode;
        this.brand = brand;
        this.model = model;
        this.transmissionType=transmissionType;
        this.carType=carType;
        this.passengerCapacity=passengerCapacity;
        this.dailyPrice=dailyPrice;
        this.mileage=mileage;
        this.status=status;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Car.CarStatus getStatus() {
        return status;
    }

    public void setStatus(Car.CarStatus status) {
        this.status = status;
    }
}
