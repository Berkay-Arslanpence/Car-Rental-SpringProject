package com.rentCar.carRental_app;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reservationNumber;

    private LocalDateTime creationDate;
    private LocalDateTime pickUpDateTime;
    private LocalDateTime dropOffDateTime;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Member member;

    @ManyToOne
    private com.rentCar.carRental_app.model.Car car;

    @ManyToOne
    private Location pickUpLocation;

    @ManyToOne
    private Location dropOffLocation;

    @ManyToMany
    private List<Equipment> equipmentList;

    @ManyToMany
    private List<Service> serviceList;

    public enum Status {
        ACTIVE, PENDING, CONFIRMED, COMPLETED, CANCELLED, NONE
    }
}


