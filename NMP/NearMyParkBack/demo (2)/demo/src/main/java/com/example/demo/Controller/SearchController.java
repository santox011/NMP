package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.demo.service.ParkingService;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "*")
public class SearchController {

    @Autowired
    private ParkingService service;

    @PostMapping
    public List<Map<String, Object>> search(@RequestBody Map<String, Object> req) {

        String location = (String) req.get("location");
        String address = (String) req.get("address");
        String vehicleType = (String) req.get("vehicleType");

        double lat = Double.parseDouble(req.get("latitude").toString());
        double lng = Double.parseDouble(req.get("longitude").toString());

        return service.search(location, address, vehicleType, lat, lng);
    }
}