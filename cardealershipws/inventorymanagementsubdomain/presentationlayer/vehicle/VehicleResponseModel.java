package com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseModel {

    private String vehicleId;

    private String inventoryId;

    private String make;

    private String model;

    private Integer year;

    private Status status;

    private UsageType usageType;

    private MSRP msrp;

    private Options options;

    private Manufacturer manufacturer;
}
