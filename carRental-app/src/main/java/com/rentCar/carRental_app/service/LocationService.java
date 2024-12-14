package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.LocationDTO;
import com.rentCar.carRental_app.mapper.LocationMapper;
import com.rentCar.carRental_app.model.Location;
import com.rentCar.carRental_app.repo.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        List<LocationDTO> locationDTOList = new ArrayList<>();
        for (Location location : locations) {
            locationDTOList.add(LocationMapper.locationToLocationDTO(location));
        }
        return locationDTOList;
    }

    public LocationDTO getLocationByCode(String code) {
        Location location = locationRepository.findByCode(code);
        return location != null ? LocationMapper.locationToLocationDTO(location) : null;
    }
}
