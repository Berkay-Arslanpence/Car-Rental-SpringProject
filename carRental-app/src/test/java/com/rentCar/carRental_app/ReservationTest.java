package com.rentCar.carRental_app;

import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.dto.ServiceDTO;
import com.rentCar.carRental_app.mapper.EquipmentMapper;
import com.rentCar.carRental_app.mapper.ReservationMapper;
import com.rentCar.carRental_app.mapper.ServiceMapper;
import com.rentCar.carRental_app.model.*;
import com.rentCar.carRental_app.service.*;
import com.rentCar.carRental_app.repo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    ServiceRepository serviceRepositoryNM;

    @Autowired
    ReservationService reservationServiceNM;

    @Autowired
    ReservationRepository reservationRepositoryNM;

    @Autowired
    private ServiceService serviceService;
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentRepository equipmentRepositoryNM;;


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
        ReservationDTO result = reservationServiceNM.makeReservation("CAR002", 5, 1L, "LOC001", "LOC002",
                EquipmentMapper.EquipmentDTOListToEquipmentList(equipmentService.getAllEquipment()),
                ServiceMapper.ServiceDTOListtoServiceList(serviceService.getAllServices())) ;
        reservationRepository.save(ReservationMapper.ReservationDTOToReservation(result));
        assertThat(result).isNotNull();
    }

    @Test
    void testDeleteReservationByReservationNumber() {
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation("CAR002", 5, 1L, "LOC001", "LOC002",
                EquipmentMapper.EquipmentDTOListToEquipmentList(equipmentService.getAllEquipment()),
                ServiceMapper.ServiceDTOListtoServiceList(serviceService.getAllServices())) ;
        Reservation reservation = ReservationMapper.ReservationDTOToReservation(reservationDTO);
        reservationRepositoryNM.save(reservation);

        boolean result = reservationServiceNM.deleteReservationByReservationNumber(reservation.getReservationNumber());

        assertThat(result).isTrue();
    }

    @Test
    void testAddAdditionalService() {
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation("CAR002", 5, 1L, "LOC001", "LOC002",
                EquipmentMapper.EquipmentDTOListToEquipmentList(equipmentService.getAllEquipment()),
                ServiceMapper.ServiceDTOListtoServiceList(serviceService.getAllServices())) ;
        Reservation reservation = ReservationMapper.ReservationDTOToReservation(reservationDTO);
        reservationRepositoryNM.save(reservation);
        System.out.println(reservation);
        Services service = new Services();
        serviceRepositoryNM.save(service);

        boolean result = reservationServiceNM.AddAdditionalService(reservation.getReservationNumber(), service.getId());

        assertThat(result).isTrue();
    }

    @Test
    void testAddAdditionalEquipment() {
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation("CAR002", 5, 1L, "LOC001", "LOC002",
        EquipmentMapper.EquipmentDTOListToEquipmentList(equipmentService.getAllEquipment()),
        ServiceMapper.ServiceDTOListtoServiceList(serviceService.getAllServices())) ;
        Reservation reservation = ReservationMapper.ReservationDTOToReservation(reservationDTO);
        reservationRepositoryNM.save(reservation);
        Equipment equipment = new Equipment();
        equipmentRepositoryNM.save(equipment);

        boolean result = reservationServiceNM.AddAdditionalEquipment(reservation.getReservationNumber(), equipment.getId());

        assertThat(result).isTrue();
    }

    @Test
    void testCancelReservation() {
        Reservation reservation = new Reservation();
        Car car = new Car();
        reservation.setCar(car);
        when(reservationRepository.findByReservationNumber("RES123")).thenReturn(reservation);

        boolean result = reservationService.CancelReservation("RES123");

        assertTrue(result);
        assertSame(Car.CarStatus.AVAILABLE, reservation.getCar().getStatus());
        verify(reservationRepository, times(1)).save(reservation);
        verify(carRepository, times(1)).save(car);
    }
}
