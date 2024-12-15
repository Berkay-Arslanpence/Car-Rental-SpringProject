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

import java.util.ArrayList;
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
    @Autowired
    private MemberRepository memberRepositoryNM;;


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
        // Create and save a member
        Member member = new Member();
        member.setName("John Doe");
        member.setEmail("johndoe@example.com");
        member.setPhone("1234567890");
        memberRepositoryNM.save(member);

        // Create and save a list of equipment
        List<Equipment> equipmentList = new ArrayList<>();
        Equipment equipment1 = new Equipment();
        equipment1.setName("GPS");
        equipment1.setPrice(10.0);
        equipmentRepositoryNM.save(equipment1);
        equipmentList.add(equipment1);

        // Create and save a list of services
        List<Services> serviceList = new ArrayList<>();
        Services service1 = new Services();
        service1.setName("Insurance");
        service1.setPrice(50.0);
        serviceRepositoryNM.save(service1);
        serviceList.add(service1);

        // Make reservation
        ReservationDTO result = reservationServiceNM.makeReservation(
                "CAR002",
                5,
                member.getId(),
                "LOC001",
                "LOC002",
                equipmentList,
                serviceList
        );
        assertThat(result).isNotNull();
    }

    @Test
    void testDeleteReservationByReservationNumber() {
        // Create and save a member
        Member member = new Member();
        member.setName("Jane Doe");
        member.setEmail("janedoe@example.com");
        member.setPhone("0987654321");
        memberRepositoryNM.save(member);

        // Create and save a list of equipment
        List<Equipment> equipmentList = new ArrayList<>();
        Equipment equipment1 = new Equipment();
        equipment1.setName("Child Seat");
        equipment1.setPrice(15.0);
        equipmentRepositoryNM.save(equipment1);
        equipmentList.add(equipment1);

        // Create and save a list of services
        List<Services> serviceList = new ArrayList<>();
        Services service1 = new Services();
        service1.setName("Roadside Assistance");
        service1.setPrice(30.0);
        serviceRepositoryNM.save(service1);
        serviceList.add(service1);

        // Make reservation
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation(
                "CAR002",
                5,
                member.getId(),
                "LOC001",
                "LOC002",
                equipmentList,
                serviceList
        );
        //Reservation reservation = ReservationMapper.ReservationDTOToReservation(reservationDTO);
        //reservationRepositoryNM.save(reservation);

        boolean result = reservationServiceNM.deleteReservationByReservationNumber(reservationDTO.getReservationNumber());
        assertThat(result).isTrue();
    }

    @Test
    void testAddAdditionalService() {
        // Create and save a member
        Member member = new Member();
        member.setName("Sam Smith");
        member.setEmail("samsmith@example.com");
        member.setPhone("5678901234");
        memberRepositoryNM.save(member);

        // Create and save a list of equipment
        List<Equipment> equipmentList = new ArrayList<>();
        Equipment equipment1 = new Equipment();
        equipment1.setName("Roof Box");
        equipment1.setPrice(20.0);
        equipmentRepositoryNM.save(equipment1);
        equipmentList.add(equipment1);

        // Create and save a list of services
        List<Services> serviceList = new ArrayList<>();
        Services service1 = new Services();
        service1.setName("Additional Driver");
        service1.setPrice(40.0);
        serviceRepositoryNM.save(service1);
        serviceList.add(service1);

        // Make reservation
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation(
                "CAR002",
                5,
                member.getId(),
                "LOC001",
                "LOC002",
                equipmentList,
                serviceList
        );

        // Add additional service
        Services additionalService = new Services();
        additionalService.setName("Full Coverage");
        additionalService.setPrice(60.0);
        serviceRepositoryNM.save(additionalService);

        boolean result = reservationServiceNM.AddAdditionalService(reservationDTO.getReservationNumber(), additionalService.getId());
        assertThat(result).isTrue();
    }

    @Test
    void testAddAdditionalEquipment() {
        // Create and save a member
        Member member = new Member();
        member.setName("Alice Brown");
        member.setEmail("alicebrown@example.com");
        member.setPhone("8765432109");
        memberRepositoryNM.save(member);

        // Create and save a list of equipment
        List<Equipment> equipmentList = new ArrayList<>();
        Equipment equipment1 = new Equipment();
        equipment1.setName("Snow Chains");
        equipment1.setPrice(25.0);
        equipmentRepositoryNM.save(equipment1);
        equipmentList.add(equipment1);

        // Create and save a list of services
        List<Services> serviceList = new ArrayList<>();
        Services service1 = new Services();
        service1.setName("Navigation System");
        service1.setPrice(35.0);
        serviceRepositoryNM.save(service1);
        serviceList.add(service1);

        // Make reservation
        ReservationDTO reservationDTO = reservationServiceNM.makeReservation(
                "CAR002",
                5,
                member.getId(),
                "LOC001",
                "LOC002",
                equipmentList,
                serviceList
        );

        // Add additional equipment
        Equipment additionalEquipment = new Equipment();
        additionalEquipment.setName("Bike Rack");
        additionalEquipment.setPrice(30.0);
        equipmentRepositoryNM.save(additionalEquipment);

        boolean result = reservationServiceNM.AddAdditionalEquipment(reservationDTO.getReservationNumber(), additionalEquipment.getId());
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
