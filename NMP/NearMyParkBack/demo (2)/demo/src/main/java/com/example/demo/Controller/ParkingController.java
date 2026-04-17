package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.demo.Model.Parking;
import com.example.demo.repository.ParkingRepository;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin(origins = "*")
public class ParkingController {

    @Autowired
    private ParkingRepository repo;

    // ✅ ADD PARKING
    @PostMapping("/add")
    public Parking addParking(@RequestBody Parking parking) {
        return repo.save(parking);
    }

    // ✅ MY PARKINGS
    @GetMapping("/my/{userId}")
    public List<Parking> getMy(@PathVariable String userId) {
        return repo.findByUserId(userId);
    }

    // ✅ BOOK / UNBOOK
    @PutMapping("/status/{id}")
    public Parking updateStatus(@PathVariable Long id, @RequestBody Map<String, Boolean> req) {

        Parking p = repo.findById(id).orElseThrow();

        p.setBooked(req.get("booked"));

        return repo.save(p);
    }
}