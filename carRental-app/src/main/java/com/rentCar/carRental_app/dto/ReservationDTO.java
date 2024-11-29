package com.rentCar.carRental_app.dto;

import java.util.Date;

public class ReservationDTO {
    private String reservationNumber;
    private Date creationDate;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private Date returnDate;
    private double totalAmount;
    public ReservationDTO() {
        super();
    }
    public ReservationDTO(String reservationNumber,Date creationDate, Date pickUpDateTime , Date dropOffDateTime, Date returnDate, double totalAmount) {
        super();
        this.reservationNumber=reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.returnDate = returnDate;
        this.totalAmount = totalAmount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getPickUpDateTime() {
        return pickUpDateTime;
    }

    public void setPickUpDateTime(Date pickUpDateTime) {
        this.pickUpDateTime = pickUpDateTime;
    }

    public Date getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(Date dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
