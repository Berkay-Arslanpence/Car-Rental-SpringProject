package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.model.Location;
import com.rentCar.carRental_app.dto.LocationDTO;

public class LocationMapper {
    public static LocationDTO locationToLocationDTO(Location location) {
        return new LocationDTO(location.getCode(), location.getName(), location.getAddress());
    }
    public static Location locationDTOToLocation(LocationDTO locationDTO) {
        Location location = new Location();
        location.setCode(locationDTO.getCode());
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        return location;
    }
}
