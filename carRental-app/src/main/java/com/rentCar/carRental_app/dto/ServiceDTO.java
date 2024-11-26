package com.rentCar.carRental_app.dto;

public class ServiceDTO {

    private String name;
    private double price;
    public ServiceDTO() {
        super();
    }
    public ServiceDTO(String name, double price) {
        super();
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
