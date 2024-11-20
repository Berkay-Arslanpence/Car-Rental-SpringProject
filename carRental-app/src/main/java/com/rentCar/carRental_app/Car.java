package com.rentCar.carRental_app;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String barcode;
    private String brand;
    private String model;
    private String transmissionType;
    private String carType;
    private int passengerCapacity;
    private double dailyPrice;
    private double mileage;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    public enum CarStatus {
        AVAILABLE, RESERVED, LOANED, LOST, BEING_SERVICED
    }
}
