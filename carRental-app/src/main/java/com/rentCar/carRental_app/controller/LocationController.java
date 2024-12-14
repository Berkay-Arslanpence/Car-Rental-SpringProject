package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.model.Location;
import com.rentCar.carRental_app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import com.rentCar.carRental_app.dto.LocationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        List<LocationDTO> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{code}")
    public ResponseEntity<LocationDTO> getLocationByCode(@PathVariable String code) {
        LocationDTO location = locationService.getLocationByCode(code);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }
}
