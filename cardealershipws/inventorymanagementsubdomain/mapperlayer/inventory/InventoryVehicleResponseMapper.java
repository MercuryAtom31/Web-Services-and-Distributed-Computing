package com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryVehicleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryVehicleResponseMapper {

    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    InventoryVehicleResponseModel entityToResponseModel(Inventory inventory, List<Vehicle> availableVehicles);
}
