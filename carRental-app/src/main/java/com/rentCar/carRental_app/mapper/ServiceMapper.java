package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.model.Service;

public class ServiceMapper {
    public static ServiceDTO serviceToServiceDTO(Service service) {
        return new ServiceDTO(service.getName(), service.getPrice());
    }
    public static Service serviceDTOToService(ServiceDTO serviceDTO) {
        Service service = new Service();
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrice());
        return service;
    }
}
