package com.rentCar.carRental_app;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //random kendimiz yapıcaz Generated Value kullanmadan
    private String reservationNumber;

    private Date creationDate;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Car car;

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


