package com.example.demo.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "parking_request")
public class ParkingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parkingId;      // which parking
    private String ownerId;      // parking owner (same as Parking.userId)
    private String requesterId;  // driver (mobile/userId)

    private String status = "PENDING"; // PENDING / ACCEPTED / REJECTED

    // getters/setters
    public Long getId() { return id; }

    public Long getParkingId() { return parkingId; }
    public void setParkingId(Long parkingId) { this.parkingId = parkingId; }

    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

    public String getRequesterId() { return requesterId; }
    public void setRequesterId(String requesterId) { this.requesterId = requesterId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    private String fromDate;
    private String toDate;
    private String time;

    public String getFromDate() { return fromDate; }
    public void setFromDate(String fromDate) { this.fromDate = fromDate; }

    public String getToDate() { return toDate; }
    public void setToDate(String toDate) { this.toDate = toDate; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}