package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.demo.Model.Parking;
import com.example.demo.Model.ParkingRequest;
import com.example.demo.repository.ParkingRepository;
import com.example.demo.repository.ParkingRequestRepository;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "*")
public class ParkingRequestController {

    @Autowired
    private ParkingRequestRepository reqRepo;

    @Autowired
    private ParkingRepository parkingRepo;

    // ✅ CREATE REQUEST
    @PostMapping("/create")
    public ParkingRequest create(@RequestBody Map<String, String> req) {

        Long parkingId = Long.parseLong(req.get("parkingId"));
        String requesterId = req.get("requesterId");

        Parking p = parkingRepo.findById(parkingId).orElseThrow();

        ParkingRequest r = new ParkingRequest();

        r.setParkingId(parkingId);
        r.setOwnerId(p.getUserId());
        r.setRequesterId(requesterId);
        r.setStatus("PENDING");

        // 🔥 NEW
        r.setFromDate(req.get("fromDate"));
        r.setToDate(req.get("toDate"));
        r.setTime(req.get("time"));

        return reqRepo.save(r);
    }

    // ✅ OWNER VIEW REQUESTS
    @GetMapping("/owner/{ownerId}")
    public List<ParkingRequest> getOwnerRequests(@PathVariable String ownerId) {
        return reqRepo.findByOwnerId(ownerId);
    }

    // ✅ ACCEPT REQUEST
    @PutMapping("/accept/{id}")
    public ParkingRequest accept(@PathVariable Long id) {

        ParkingRequest r = reqRepo.findById(id).orElseThrow();

        r.setStatus("ACCEPTED");

        Parking p = parkingRepo.findById(r.getParkingId()).orElseThrow();
        p.setBooked(true);
        parkingRepo.save(p);

        // ❌ reject other requests
        List<ParkingRequest> all = reqRepo.findAll();

        for (ParkingRequest req : all) {
            if (!req.getId().equals(id) &&
                    req.getParkingId().equals(r.getParkingId())) {

                req.setStatus("REJECTED");
                reqRepo.save(req);
            }
        }

        return reqRepo.save(r);
    }

    // ❌ REJECT REQUEST (optional)
    @PutMapping("/reject/{id}")
    public ParkingRequest reject(@PathVariable Long id) {

        ParkingRequest r = reqRepo.findById(id).orElseThrow();
        r.setStatus("REJECTED");

        return reqRepo.save(r);
    }
    @GetMapping("/user/{userId}")
    public List<Map<String, Object>> getUserRequests(@PathVariable String userId) {

        List<ParkingRequest> requests = reqRepo.findByRequesterId(userId);

        List<Map<String, Object>> result = new ArrayList<>();

        for (ParkingRequest r : requests) {

            Parking p = parkingRepo.findById(r.getParkingId()).orElse(null);

            Map<String, Object> map = new HashMap<>();

            map.put("requestId", r.getId());
            map.put("status", r.getStatus());

            if (p != null) {
                map.put("location", p.getLocation());
                map.put("vehicleType", p.getVehicleType());
                map.put("mobile", p.getMobile());   // 🔥 OWNER MOBILE
            }

            result.add(map);
        }

        return result;
    }
}