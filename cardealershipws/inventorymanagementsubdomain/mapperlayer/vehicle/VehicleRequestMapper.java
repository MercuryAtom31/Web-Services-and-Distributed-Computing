package com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.vehicle;

import com.benzair.cardealershipws.common.VehicleIdentifier;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle.VehicleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VehicleRequestMapper {

    @Mapping(target = "id", ignore = true)
    Vehicle requestModelToEntity(VehicleRequestModel vehicleRequestModel, VehicleIdentifier vehicleIdentifier);
}
