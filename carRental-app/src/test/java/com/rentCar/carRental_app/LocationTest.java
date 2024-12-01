package com.rentCar.carRental_app;

import com.rentCar.carRental_app.model.Location;
import com.rentCar.carRental_app.service.LocationService;
import com.rentCar.carRental_app.repo.LocationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationTest {

    @InjectMocks
    private LocationService locationService;

    @Mock
    private LocationRepository locationRepository;

    @Test
    void testGetAllLocations() {
        Location location = new Location();
        location.setCode("LOC123");
        location.setName("New York");
        when(locationRepository.findAll()).thenReturn(Arrays.asList(location));

        List<Location> result = locationService.getAllLocations();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getCode()).isEqualTo("LOC123");
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    void testGetLocationByCode() {
        Location location = new Location();
        location.setCode("LOC123");
        location.setName("New York");
        when(locationRepository.findByCode("LOC123")).thenReturn(location);

        Location result = locationService.getLocationByCode("LOC123");

        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo("LOC123");
        verify(locationRepository, times(1)).findByCode("LOC123");
    }
}
