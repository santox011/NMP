package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;
    private String address;
    private String vehicleType;
    private String mobile;
    private String alternateMobile;
    private double price;

    private String userId;

    private String mapLink;      // 🔥 Google map link
    private Double latitude;     // 🔥 GPS
    private Double longitude;

    private boolean booked = false;

    // ===== GETTERS & SETTERS =====

    public Long getId() { return id; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getAlternateMobile() { return alternateMobile; }
    public void setAlternateMobile(String alternateMobile) { this.alternateMobile = alternateMobile; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getMapLink() { return mapLink; }
    public void setMapLink(String mapLink) { this.mapLink = mapLink; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
}