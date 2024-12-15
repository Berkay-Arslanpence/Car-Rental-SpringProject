package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.CarDTO;
import com.rentCar.carRental_app.dto.RentedCarDTO;
import com.rentCar.carRental_app.dto.ReservationDTO;
import com.rentCar.carRental_app.mapper.CarMapper;
import com.rentCar.carRental_app.mapper.ReservationMapper;
import com.rentCar.carRental_app.model.*;
import com.rentCar.carRental_app.repo.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
    }

    public boolean returnCar(String reservationNumber){
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);

        if(reservation == null){
            return false;
        }

        Car car = reservation.getCar();
        if(car == null){
            return false;
        }

        //try{
            reservation.setStatus(Reservation.Status.COMPLETED);
            reservation.setReturnDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            reservationRepository.save(reservation);

            carRepository.updateCarStatusToAvailable(car.getBarcode());
            return true;
        //} catch (Exception e) {
          //  e.printStackTrace();
        //    return false;
        //}
    }
    @Transactional
    public ReservationDTO makeReservation(String carBarcode, int dayCount, Long memId, String pickUpLocCode, String dropOffLocCode, List<Equipment> addiEquipments, List<Services> addiServices) {
        Car c = carRepository.findCarByBarcode(carBarcode);
        Location pickL=locationRepository.findByCode(pickUpLocCode);
        Location dropL=locationRepository.findByCode(dropOffLocCode);
          Member m=memberRepository.findMemById(memId);
          if(c!=null && pickL!=null && dropL!=null && m!=null){
              if (carRepository.isAvailable(carBarcode)) {
                  Reservation reservation = new Reservation(
                          Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()),
                          Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant()),
                          Date.from(LocalDateTime.now().plusDays(1 + dayCount).atZone(ZoneId.systemDefault()).toInstant()),
                          Date.from(LocalDateTime.now().plusDays(1 + dayCount).atZone(ZoneId.systemDefault()).toInstant()),
                          locationRepository.findByCode(pickUpLocCode),
                          locationRepository.findByCode(dropOffLocCode),
                          memberRepository.findMemById(memId)
                  );
                  reservation.setCar(c);
                  reservation.setStatus(Reservation.Status.PENDING);
                  reservation.setServiceList(addiServices);
                  reservation.setEquipmentList(addiEquipments);
                  reservation.setTotalAmount(calculateTotalAmount(dayCount, c.getDailyPrice(), addiServices, addiEquipments));
                  reservation.setReservationNumber(generateReservationNumber());
                  reservationRepository.save(reservation);
                  c.setStatus(Car.CarStatus.LOANED);
                  carRepository.save(c);
                  return ReservationMapper.ReservationToReservationDTO(reservation);
          }
              else{
                  System.out.println("Reservation Failed!, The car is not available");
                  return null;
              }

        }
          else {
              if(c==null)
                  System.out.println("car is null");
              if(m==null)
                  System.out.println("member is null");
              if(pickL==null)
                  System.out.println("car is null");
              if(dropL==null)
                  System.out.println("car is null");
              return null;
        }

    }


    public List<RentedCarDTO> getAllRentedCars(){
        List<Reservation> reservations=reservationRepository.findAllReservationsWithLoanedOrReservedCars();

        return reservations.stream().map(reservation -> {
            RentedCarDTO dto = new RentedCarDTO();
            dto.setBrand(reservation.getCar().getBrand());
            dto.setModel(reservation.getCar().getModel());
            dto.setCarType(reservation.getCar().getCarType());
            dto.setTransmissionType(reservation.getCar().getTransmissionType());
            dto.setBarcode(reservation.getCar().getBarcode());
            dto.setReservationNumber(reservation.getReservationNumber());
            dto.setMemberName(reservation.getMember().getName());
            dto.setDropOffDateTime(reservation.getDropOffDateTime());
            dto.setDropOffLocation(reservation.getDropOffLocation().getName());

            long differenceInMillis = Math.abs(reservation.getDropOffDateTime().getTime() - reservation.getPickUpDateTime().getTime());
            long differenceInDays = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);
            dto.setReservationDayCount((int) differenceInDays);

            return dto;
        }).collect(Collectors.toList());
    }

    public boolean deleteReservationByReservationNumber(String reservationNumber) {

            Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
            if(reservation == null){
                return false;
            }
            Car c=reservation.getCar();
            if(c==null){
                return false;
            }
            reservationRepository.deleteReservation(reservationNumber);
            c.setStatus(Car.CarStatus.AVAILABLE);
            carRepository.save(c);
            return true;
    }

    public double calculateTotalAmount(int dayCount, double cardailyPrice, List<Services> serviceList, List<Equipment> equipmentList){
        double totalservicePrice =0 ;
        for(int i=0;i< serviceList.size();i++){
            totalservicePrice += serviceList.get(i).getPrice();
        }

        double totalequipmentPrice =0 ;
        for(int i=0;i< equipmentList.size();i++){
            totalequipmentPrice += equipmentList.get(i).getPrice();
        }
      return dayCount*cardailyPrice +totalservicePrice+totalequipmentPrice;

    }

    @Transactional
    public boolean AddAdditionalService(String reservationNumber, long serviceId){
        try{
            Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
            if(reservation == null){
                return false; //404-notfound
            }

            Services additionalService = serviceRepository.findById(serviceId);
            if(additionalService == null){
                return false; //404-service not found
            }

            if (reservation.getServiceList().contains(additionalService)){
                return false; // service already added exception
            }

            reservation.getServiceList().add(additionalService);
            reservationRepository.save(reservation);

            return true; //200-success
        } catch (Exception e) {
            e.printStackTrace();
            return false; //500-exception
        }
    }

    @Transactional
    public boolean AddAdditionalEquipment(String reservationNumber, long equipmentId){
        try {
            Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
            if(reservation == null){
                return false;
            }

            Equipment additionalEquipment = equipmentRepository.findById(equipmentId);
            if(additionalEquipment == null){
                return false;
            }
            if (reservation.getEquipmentList().contains(additionalEquipment)){
                return false;
            }

            reservation.getEquipmentList().add(additionalEquipment);
            reservationRepository.save(reservation);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean CancelReservation(String reservationNumber){
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber);
        if(reservation == null){
            return false;
        }
        else{
            reservation.setStatus(Reservation.Status.CANCELLED);
            Car  c=reservation.getCar();
            c.setStatus(Car.CarStatus.AVAILABLE);
            carRepository.save(c);
            reservationRepository.save(reservation);
            return true;
        }
    }
    public String generateReservationNumber(){
            // Generate a random 8-digit number
            int number=(int) (Math.random() * 90000000) + 10000000;
            String reservationNumber=String.valueOf(number);
            if(reservationRepository.existsReservationWithNumber(reservationNumber)){
                return generateReservationNumber();
            }
            else{
                return reservationNumber;
            }

    }

}
