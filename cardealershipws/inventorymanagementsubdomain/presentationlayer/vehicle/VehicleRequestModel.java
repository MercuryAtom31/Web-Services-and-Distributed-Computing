package com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle;

import com.benzair.cardealershipws.common.InventoryIdentifier;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.MSRP;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Manufacturer;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Options;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestModel {

    private String make;

    private String model;

    private Integer year;

    private String status;

    private String usageType;

    private MSRP msrp;

    private Options options;

    private Manufacturer manufacturer;

    private InventoryIdentifier inventoryIdentifier;
}
