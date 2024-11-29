package com.rentCar.carRental_app.dto;

import com.rentCar.carRental_app.model.Car;

public class RentedCarDTO extends CarDTO {
    private String reservationNumber;
    private String memberName;
    private String dropOffDateTime;
    private String dropOffLocation;
    private String reservationDayCount;

    public RentedCarDTO() {super();}
    public RentedCarDTO(String brand, String model, String carType, String transmissionType, String barcode, String reservationNumber, String memberName, String dropOffDateTime, String dropOffLocation, String reservationDayCount) {

    }
}
