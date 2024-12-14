package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.mapper.ServiceMapper;
import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.repo.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceDTO> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        for (Services service : services) {
            serviceDTOList.add(ServiceMapper.serviceToServiceDTO(service));
        }
        return serviceDTOList;
    }

    public ServiceDTO getServiceByName(String name) {
        Services service = serviceRepository.findByName(name);
        return service != null ? ServiceMapper.serviceToServiceDTO(service) : null;
    }
}
