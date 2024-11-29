package com.rentCar.carRental_app;

import com.rentCar.carRental_app.model.*;
import com.rentCar.carRental_app.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CarRepository carRepository,
            MemberRepository memberRepository,
            LocationRepository locationRepository,
            EquipmentRepository equipmentRepository,
            ServiceRepository serviceRepository
    ) {
        return args -> {
            // Add sample members
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

            // Add sample cars
            Car car1 = new Car();
            car1.setBarcode("CAR001");
            car1.setBrand("Toyota");
            car1.setModel("Corolla");
            car1.setTransmissionType("Automatic");
            car1.setCarType("Economy");
            car1.setDailyPrice(50.0);
            car1.setStatus(Car.CarStatus.AVAILABLE);

            Car car2 = new Car();
            car2.setBarcode("CAR002");
            car2.setBrand("Honda");
            car2.setModel("Civic");
            car2.setTransmissionType("Manual");
            car2.setCarType("Standard");
            car2.setDailyPrice(70.0);
            car2.setStatus(Car.CarStatus.AVAILABLE);

            carRepository.saveAll(List.of(car1, car2));

            // Add sample locations
            Location location1 = new Location();
            location1.setCode("LOC001");
            location1.setName("Istanbul Airport");
            location1.setAddress("Istanbul, Turkey");

            Location location2 = new Location();
            location2.setCode("LOC002");
            location2.setName("Izmir City Center");
            location2.setAddress("Izmir, Turkey");

            locationRepository.saveAll(List.of(location1, location2));

            // Add sample equipment
            Equipment equipment1 = new Equipment();
            equipment1.setName("Snow Tyres");
            equipment1.setPrice(20.0);

            Equipment equipment2 = new Equipment();
            equipment2.setName("Baby Seat");
            equipment2.setPrice(15.0);

            equipmentRepository.saveAll(List.of(equipment1, equipment2));

            // Add sample services
            Services service1 = new Services();
            service1.setName("Roadside Assistance");
            service1.setPrice(25.0);

            Services service2 = new Services();
            service2.setName("Additional Driver");
            service2.setPrice(30.0);

            serviceRepository.saveAll(List.of(service1, service2));

            System.out.println("Database initialized with sample data.");
        };
    }
}
