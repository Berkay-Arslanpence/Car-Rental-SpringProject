package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.dto.EquipmentDTO;
import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.model.Equipment;
import com.rentCar.carRental_app.model.Services;

import java.util.ArrayList;
import java.util.List;

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
    public static List<ServiceDTO> ServicetoServiceDTOList(List<Services> list) {
        List<ServiceDTO> serviceDTOList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            serviceDTOList.add(serviceToServiceDTO(list.get(i)));
        }
        return serviceDTOList;
    }
    public static List<Services> ServiceDTOListtoServiceList(List<ServiceDTO> list) {
        List<Services> serviceList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            serviceList.add(serviceDTOToService((list.get(i))));
        }
        return serviceList;
    }
}
