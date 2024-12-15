package com.rentCar.carRental_app;

import com.rentCar.carRental_app.mapper.ServiceMapper;
import com.rentCar.carRental_app.model.Services;
import com.rentCar.carRental_app.service.ServiceService;
import com.rentCar.carRental_app.repo.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ServiceTest {

    @InjectMocks
    private ServiceService serviceService;

    @Mock
    private ServiceRepository serviceRepository;

    @Test
    void testGetAllServices() {
        Services service = new Services();
        service.setName("Car Wash");
        when(serviceRepository.findAll()).thenReturn(Arrays.asList(service));

        List<Services> result = ServiceMapper.ServiceDTOListtoServiceList(serviceService.getAllServices());

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Car Wash");
        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    void testGetServiceByName() {
        Services service = new Services();
        service.setName("Car Wash");
        when(serviceRepository.findByName("Car Wash")).thenReturn(service);

        Services result = ServiceMapper.serviceDTOToService(serviceService.getServiceByName("Car Wash")) ;

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Car Wash");
        verify(serviceRepository, times(1)).findByName("Car Wash");
    }
}
