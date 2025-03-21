package com.rentCar.carRental_app;

import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.mapper.ReservationMapper;
import com.rentCar.carRental_app.model.*;
import com.rentCar.carRental_app.repo.*;
import com.rentCar.carRental_app.service.CarService;
import com.rentCar.carRental_app.service.ReservationService;
import com.rentCar.carRental_app.dto.CarDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            ReservationService reservationService,
            MemberRepository memberRepository,
            LocationRepository locationRepository,
            ReservationRepository reservationRepository,
            EquipmentRepository equipmentRepository,
            ServiceRepository serviceRepository,
            CarRepository carRepository, CarService carService) {
        return args -> {
            // Step 1: Clear existing data
            reservationRepository.deleteAll();
            memberRepository.deleteAll();
            locationRepository.deleteAll();
            carRepository.deleteAll();
            equipmentRepository.deleteAll();
            serviceRepository.deleteAll();

            // Step 2: Add sample members
            Member member1 = new Member();
            member1.setName("John Doe");
            member1.setAddress("123 Main St");
            member1.setEmail("john.doe@example.com");
            member1.setPhone("1234567890");
            member1.setDrivingLicenseNumber("DL123456");

            Member member2 = new Member();
            member2.setName("Jane Smith");
            member2.setAddress("456 Elm St");
            member2.setEmail("jane.smith@example.com");
            member2.setPhone("9876543210");
            member2.setDrivingLicenseNumber("DL654321");

            memberRepository.saveAll(List.of(member1, member2));

            // Step 3: Add sample cars
            Car car1 = new Car();
            car1.setBarcode("CAR001");
            car1.setBrand("Toyota");
            car1.setModel("Corolla");
            car1.setTransmissionType("Automatic");
            car1.setCarType("Economy");
            car1.setDailyPrice(50.0);
            car1.setStatus(Car.CarStatus.AVAILABLE);
            car1.setLicensePlate("10BM971");
            car1.setPassengerCapacity(4);
            car1.setMileage(10000.0);

            Car car2 = new Car();
            car2.setBarcode("CAR002");
            car2.setBrand("Honda");
            car2.setModel("Civic");
            car2.setTransmissionType("Manual");
            car2.setCarType("Standard");
            car2.setDailyPrice(70.0);
            car2.setStatus(Car.CarStatus.AVAILABLE);
            car2.setLicensePlate("91SK293");
            car2.setPassengerCapacity(4);
            car2.setMileage(15000.0);

            carRepository.saveAll(List.of(car1, car2));

            // Step 4: Add sample locations
            Location location1 = new Location();
            location1.setCode("LOC001");
            location1.setName("Istanbul Airport");
            location1.setAddress("Istanbul, Turkey");

            Location location2 = new Location();
            location2.setCode("LOC002");
            location2.setName("Izmir City Center");
            location2.setAddress("Izmir, Turkey");

            locationRepository.saveAll(List.of(location1, location2));

            // Step 5: Add sample equipment
            Equipment equipment1 = new Equipment();
            equipment1.setName("Snow Tyres");
            equipment1.setPrice(20.0);

            Equipment equipment2 = new Equipment();
            equipment2.setName("Baby Seat");
            equipment2.setPrice(15.0);

            Equipment equipment3 = new Equipment();
            equipment3.setName("Additional Baggage");
            equipment3.setPrice(12.0);

            equipmentRepository.saveAll(List.of(equipment1, equipment2, equipment3));

            // Step 6: Add sample services
            Services service1 = new Services();
            service1.setName("Roadside Assistance");
            service1.setPrice(25.0);

            Services service2 = new Services();
            service2.setName("Additional Driver");
            service2.setPrice(30.0);

            Services service3 = new Services();
            service3.setName("Towing Service");
            service3.setPrice(20.0);

            serviceRepository.saveAll(List.of(service1, service2,service3));

            // Step 7: Create reservation using ReservationService
            List<String> equipmentList = List.of(equipment1.getName(), equipment2.getName());
            List<String> serviceList = List.of(service1.getName(), service2.getName());
            reservationService.makeReservation(
                    car1.getBarcode(),
                    5,
                    member1.getId(),
                    location1.getCode(),
                    location2.getCode(),
                    equipmentList,
                    serviceList
            );
            System.out.println("Database initialized with sample data.");
            List<CarDTO> l=carService.searchAvailableCars("SUV","Automatic");
            for(int i=0;i<l.size();i++){
                System.out.println(l.get(i).getBrand());
            }
        };

    }
}
