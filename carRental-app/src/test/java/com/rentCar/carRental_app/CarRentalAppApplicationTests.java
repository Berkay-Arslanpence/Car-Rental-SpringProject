package com.rentCar.carRental_app;

import com.rentCar.carRental_app.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CarRentalAppApplicationTests {
	@Autowired
 CarService carService;
	@Test
	void contextLoads() {
		boolean ret=carService.deleteCarByBarcode("234234234");
		assertTrue(ret);
	}

}
