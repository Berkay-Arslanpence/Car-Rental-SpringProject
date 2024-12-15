package com.rentCar.carRental_app.dto;

import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.model.Services;

import java.util.List;

public class MakeReservationRequestDTO {
    private String carBarcode;
    private int dayCount;
    private Long memberId;
    private String pickupLocationCode;
    private String dropOffLocationCode;
    private List<String> addiServices;
    private List<String> addiEquipments;

    // Getters and Setters
    public String getCarBarcode() {
        return carBarcode;
    }

    public void setCarBarcode(String carBarcode) {
        this.carBarcode = carBarcode;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getPickupLocationCode() {
        return pickupLocationCode;
    }

    public void setPickupLocationCode(String pickupLocationCode) {
        this.pickupLocationCode = pickupLocationCode;
    }

    public String getDropOffLocationCode() {
        return dropOffLocationCode;
    }

    public void setDropOffLocationCode(String dropOffLocationCode) {
        this.dropOffLocationCode = dropOffLocationCode;
    }

    public List<String> getAddiServices() {
        return addiServices;
    }

    public void setAddiServices(List<String> addiServices) {
        this.addiServices = addiServices;
    }

    public List<String> getAddiEquipments() {
        return addiEquipments;
    }

    public void setAddiEquipments(List<String> addiEquipments) {
        this.addiEquipments = addiEquipments;
    }
}
