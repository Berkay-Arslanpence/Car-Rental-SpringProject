package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.model.Service;
import com.rentCar.carRental_app.repo.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceByName(String name) {
        return serviceRepository.findByName(name);
    }
}
