package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

    List<Parking> findByUserId(String userId);

    List<Parking> findByVehicleType(String vehicleType);

    List<Parking> findByLocationContainingIgnoreCaseAndVehicleType(String location, String vehicleType);

    List<Parking> findByAddressContainingIgnoreCaseAndVehicleType(String address, String vehicleType);
}