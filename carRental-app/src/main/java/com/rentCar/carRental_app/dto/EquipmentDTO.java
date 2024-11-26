package com.rentCar.carRental_app.dto;

public class EquipmentDTO {
    private String name;
    private double price;

    public EquipmentDTO() {
        super();
    }
    public EquipmentDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
