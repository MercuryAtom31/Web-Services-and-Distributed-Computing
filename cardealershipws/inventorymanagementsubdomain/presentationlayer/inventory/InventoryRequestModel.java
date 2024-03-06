package com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryType;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestModel {

    private InventoryType type;

    private List<Vehicle> vehicles;
}
