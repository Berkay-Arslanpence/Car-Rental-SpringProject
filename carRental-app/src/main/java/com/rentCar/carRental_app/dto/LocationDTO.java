package com.rentCar.carRental_app.dto;

public class LocationDTO {

    private String code;
    private String name;
    private String address;
    public LocationDTO() {
        super();
    }
    public LocationDTO(String code, String name, String address) {
        super();
        this.code = code;
        this.name = name;
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
