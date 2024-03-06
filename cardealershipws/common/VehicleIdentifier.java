package com.benzair.cardealershipws.common;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class VehicleIdentifier {

    @Column(name = "vehicle_id")
    private String vehicleId;

    public VehicleIdentifier() {
        this.vehicleId = UUID.randomUUID().toString();
    }

    public VehicleIdentifier(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
