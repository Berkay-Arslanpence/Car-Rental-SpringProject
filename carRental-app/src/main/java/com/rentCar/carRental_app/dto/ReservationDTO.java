package com.rentCar.carRental_app.dto;

import java.util.Date;

public class ReservationDTO {
    private Date creationDate;
    private Date pickUpDateTime;
    private Date dropOffDateTime;
    private Date returnDate;
    public ReservationDTO() {
        super();
    }
    public ReservationDTO(Date creationDate, Date pickUpDateTime , Date dropOffDateTime, Date returnDate) {
        super();
        this.creationDate = creationDate;
        this.pickUpDateTime = pickUpDateTime;
        this.dropOffDateTime = dropOffDateTime;
        this.returnDate = returnDate;
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
}
