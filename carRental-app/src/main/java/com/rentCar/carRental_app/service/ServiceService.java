package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.repo.ServiceRepository;

import java.util.List;


public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Services getServiceByName(String name) {
        return serviceRepository.findByName(name);
    }
}
