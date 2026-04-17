package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.example.demo.Model.Parking;
import com.example.demo.repository.ParkingRepository;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository repo;

    // 🔥 Haversine formula
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

    public List<Map<String, Object>> search(String location, String address,
                                            String vehicleType,
                                            double userLat, double userLng) {

        List<Parking> list;

        if (location != null && !location.isEmpty()) {
            list = repo.findByLocationContainingIgnoreCaseAndVehicleType(location, vehicleType);
        } else if (address != null && !address.isEmpty()) {
            list = repo.findByAddressContainingIgnoreCaseAndVehicleType(address, vehicleType);
        } else {
            list = repo.findByVehicleType(vehicleType);
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Parking p : list) {

            // ❌ skip booked
            if (p.isBooked()) continue;

            double distance = 9999;

            if (p.getLatitude() != null && p.getLongitude() != null) {
                distance = calculateDistance(userLat, userLng,
                        p.getLatitude(), p.getLongitude());
            }

            Map<String, Object> map = new HashMap<>();
            map.put("parking", p);
            map.put("distance", distance);

            result.add(map);
        }

        result.sort(Comparator.comparing(m -> (Double) m.get("distance")));

        return result;
    }
}