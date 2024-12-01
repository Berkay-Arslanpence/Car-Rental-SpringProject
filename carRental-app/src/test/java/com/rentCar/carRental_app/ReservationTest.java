package com.rentCar.carRental_app;

import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.model.*;
import com.rentCar.carRental_app.service.CarService;
import com.rentCar.carRental_app.service.LocationService;
import com.rentCar.carRental_app.service.MemberService;
import com.rentCar.carRental_app.service.ReservationService;
import com.rentCar.carRental_app.repo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReservationTest {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private EquipmentRepository equipmentRepository;

    @Autowired
    CarService carServiceNM;

    @Autowired
    LocationService locationServiceNM;

    @Autowired
    MemberService memberServiceNM;

    @Autowired
    ReservationService reservationServiceNM;

    @Test
    void testReturnCar() {
        Reservation reservation = new Reservation();
        reservation.setReservationNumber("RES123");
        Car car = new Car();
        car.setBarcode("CAR001");
        reservation.setCar(car);

        when(reservationRepository.findByReservationNumber("RES123")).thenReturn(reservation);

        boolean result = reservationService.returnCar("RES123");

        assertThat(result).isTrue();
        verify(reservationRepository, times(1)).save(reservation);
        verify(carRepository, times(1)).updateCarStatusToAvailable("CAR001");
    }

    @Test
    void testMakeReservation() {
        Car car = new Car();
        car.setBarcode("CAR001");
        Location location1 = new Location();
        location1.setCode("NYC");
        Location location2 = new Location();
        location2.setCode("LA");
        when(carRepository.findCarByBarcode("CAR001")).thenReturn(car);
        when(carRepository.isAvailable("CAR001")).thenReturn(true);
        when(locationRepository.findByCode("NYC")).thenReturn(location1);
        when(locationRepository.findByCode("LA")).thenReturn(location2);
        when(memberRepository.findMemById(1L)).thenReturn(new Member());

        ReservationDTO result = reservationService.makeReservation("CAR001", 5, 1L, "NYC", "LA", null, null);

        assertThat(result).isNotNull();
        verify(reservationRepository, times(1)).save(any(Reservation.class));
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void testDeleteReservationByReservationNumber() {
        when(reservationRepository.deleteReservation("RES123")).thenReturn(true);

        boolean result = reservationService.deleteReservationByReservationNumber("RES123");

        assertThat(result).isTrue();
        verify(reservationRepository, times(1)).deleteReservation("RES123");
    }

    @Test
    void testAddAdditionalService() {
        Reservation reservation = new Reservation();
        Services service = new Services();
        when(reservationRepository.findByReservationNumber("RES123")).thenReturn(reservation);
        when(serviceRepository.findById(1L)).thenReturn(service);

        boolean result = reservationService.AddAdditionalService("RES123", 1L);

        assertThat(result).isTrue();
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testAddAdditionalEquipment() {
        Reservation reservation = new Reservation();
        Equipment equipment = new Equipment();
        when(reservationRepository.findByReservationNumber("RES123")).thenReturn(reservation);
        when(equipmentRepository.findById(1L)).thenReturn(equipment);

        boolean result = reservationService.AddAdditionalEquipment("RES123", 1L);

        assertThat(result).isTrue();
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testCancelReservation() {
        Reservation reservation = new Reservation();
        Car car = new Car();
        reservation.setCar(car);
        when(reservationRepository.findByReservationNumber("RES123")).thenReturn(reservation);

        boolean result = reservationService.CancelReservation("RES123");

        assertThat(result).isTrue();
        verify(reservationRepository, times(1)).save(reservation);
        verify(carRepository, times(1)).save(car);
    }
}
