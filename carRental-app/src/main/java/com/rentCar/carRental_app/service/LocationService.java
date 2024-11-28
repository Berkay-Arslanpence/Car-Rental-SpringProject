package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.model.Location;
import com.rentCar.carRental_app.repo.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationByCode(String code) {
        return locationRepository.findByCode(code);
    }
}
