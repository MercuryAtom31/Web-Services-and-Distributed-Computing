package com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.vehicle;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle.VehicleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehicleResponseMapper {

    @Mapping(expression = "java(vehicle.getVehicleIdentifier().getVehicleId())", target = "vehicleId")
    @Mapping(expression = "java(vehicle.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    VehicleResponseModel entityToResponseModel(Vehicle vehicle);

    List<VehicleResponseModel> entityListToResponseModelList(List<Vehicle> vehicleList);

//    @AfterMapping
//    default void addLink(@MappingTarget)
}
