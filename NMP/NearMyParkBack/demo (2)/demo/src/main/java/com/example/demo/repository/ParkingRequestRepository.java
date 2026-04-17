package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.ParkingRequest;

public interface ParkingRequestRepository extends JpaRepository<ParkingRequest, Long> {

    List<ParkingRequest> findByOwnerId(String ownerId);

    List<ParkingRequest> findByRequesterId(String requesterId);
}