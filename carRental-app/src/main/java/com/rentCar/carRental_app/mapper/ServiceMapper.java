package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.model.Services;

public class ServiceMapper {
    public static ServiceDTO serviceToServiceDTO(Services service) {
        return new ServiceDTO(service.getName(), service.getPrice());
    }
    public static Services serviceDTOToService(ServiceDTO serviceDTO) {
        Services service = new Services();
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrice());
        return service;
    }
}
