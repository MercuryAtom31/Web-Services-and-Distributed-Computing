package com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import com.benzair.cardealershipws.common.InventoryIdentifier;
import com.benzair.cardealershipws.common.VehicleIdentifier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor
@Setter
@Getter
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private VehicleIdentifier vehicleIdentifier;

    @Embedded
    private InventoryIdentifier inventoryIdentifier;

    private String make;

    private String model;

    @Column(name = "\"year\"") //since year is a reserved keyword in H2, we need to quote it.
    private Integer year;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @Embedded
    private MSRP msrp;

    @Embedded
    private Options options;

    @Embedded
    private Manufacturer manufacturer;

    public Vehicle(Long id, String make, String model, Integer year, Status status, UsageType usageType, MSRP msrp, Options options, Manufacturer manufacturer) {
        this.id = id;
        this.vehicleIdentifier = new VehicleIdentifier();
        this.make = make;
        this.model = model;
        this.year = year;
        this.status = status;
        this.usageType = usageType;
        this.msrp = msrp;
        this.options = options;
        this.manufacturer = manufacturer;
    }
}
