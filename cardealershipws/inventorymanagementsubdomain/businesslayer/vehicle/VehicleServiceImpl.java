package com.benzair.cardealershipws.inventorymanagementsubdomain.businesslayer.vehicle;

import com.benzair.cardealershipws.common.VehicleIdentifier;
import com.benzair.cardealershipws.inventorymanagementsubdomain.businesslayer.inventory.InventoryService;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Status;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.UsageType;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleRepository;
import com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.vehicle.VehicleRequestMapper;
import com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.vehicle.VehicleResponseMapper;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle.VehicleRequestModel;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.vehicle.VehicleResponseModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository vehicleRepository;
    private VehicleResponseMapper vehicleResponseMapper;
    private VehicleRequestMapper vehicleRequestMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleResponseMapper vehicleResponseMapper, VehicleRequestMapper vehicleRequestMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleResponseMapper = vehicleResponseMapper;
        this.vehicleRequestMapper = vehicleRequestMapper;
    }

    @Override
    public List<VehicleResponseModel> getVehicles(String inventoryId, Map<String, String> queryParams) {

        //extract the query parameters
        String status = queryParams.get("status");
        String usageType = queryParams.get("usage");

        //convert to enums
        Map<String, Status> statusMap = new HashMap<>();
        statusMap.put("available", Status.Available);
        statusMap.put("pending", Status.Pending);
        statusMap.put("sold", Status.Sold);

        Map<String, UsageType> usageTypeMap = new HashMap<>();
        usageTypeMap.put("new", UsageType.NEW);
        usageTypeMap.put("used", UsageType.USED);

        //case where both query parameters are present
        if(status != null && usageType != null){
            List<Vehicle> vehicleList = vehicleRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEqualsAndUsageTypeEquals(inventoryId, statusMap.get(status.toLowerCase()), usageTypeMap.get(usageType.toLowerCase()));

            return vehicleResponseMapper.entityListToResponseModelList(vehicleList);
        }

        //case where only status is present
        if (status != null){
            List<Vehicle> vehicleList = vehicleRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEquals(inventoryId, statusMap.get(status.toLowerCase()));

            return vehicleResponseMapper.entityListToResponseModelList(vehicleList);
        }

        if (usageType != null){
            List<Vehicle> vehicleList = vehicleRepository.findAllByInventoryIdentifier_InventoryIdAndUsageTypeEquals(inventoryId, usageTypeMap.get(usageType.toLowerCase()));

            return vehicleResponseMapper.entityListToResponseModelList(vehicleList);
        }

        return vehicleResponseMapper.entityListToResponseModelList(vehicleRepository.findAll());
    }

    @Override
    public VehicleResponseModel getVehicleByVehicleId(String vehicleId) {

        Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(vehicleId);
        return vehicleResponseMapper.entityToResponseModel(vehicle);
    }

    @Override
    public VehicleResponseModel updateVehicle(String vehicleId, VehicleRequestModel vehicleRequestModel) {

        Vehicle foundVehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(vehicleId);

        Vehicle vehicle = vehicleRequestMapper.requestModelToEntity(vehicleRequestModel, foundVehicle.getVehicleIdentifier());
        return vehicleResponseMapper.entityToResponseModel(vehicleRepository.save(vehicle));
    }

    @Override
    public VehicleResponseModel addVehicle(VehicleRequestModel vehicleRequestModel) {
        Vehicle vehicle = vehicleRequestMapper.requestModelToEntity(vehicleRequestModel, new VehicleIdentifier());

        return vehicleResponseMapper.entityToResponseModel(vehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findVehicleByVehicleIdentifier_VehicleId(vehicleId);

        vehicleRepository.delete(vehicle);
    }
}
