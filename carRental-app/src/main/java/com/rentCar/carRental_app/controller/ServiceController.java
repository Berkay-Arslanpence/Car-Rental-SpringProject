package com.rentCar.carRental_app.controller;
import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ServiceDTO> getServiceByName(@PathVariable String name) {
        ServiceDTO service = serviceService.getServiceByName(name);
        if (service == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service);
    }
}
