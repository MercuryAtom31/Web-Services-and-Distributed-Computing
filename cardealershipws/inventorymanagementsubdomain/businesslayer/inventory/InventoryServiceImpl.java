package com.benzair.cardealershipws.inventorymanagementsubdomain.businesslayer.inventory;

import com.benzair.cardealershipws.common.InventoryIdentifier;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryRepository;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleRepository;
import com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory.InventoryRequestMapper;
import com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory.InventoryResponseMapper;
import com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory.InventoryVehicleResponseMapper;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryRequestModel;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryResponseModel;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryVehicleResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{


    private InventoryRepository inventoryRepository;
    private VehicleRepository vehicleRepository;
    private InventoryRequestMapper inventoryRequestMapper;
    private InventoryResponseMapper inventoryResponseMapper;
    private InventoryVehicleResponseMapper inventoryVehicleResponseMapper;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, VehicleRepository vehicleRepository, InventoryRequestMapper inventoryRequestMapper, InventoryResponseMapper inventoryResponseMapper, InventoryVehicleResponseMapper inventoryVehicleResponseMapper) {
        this.inventoryRepository = inventoryRepository;
        this.vehicleRepository = vehicleRepository;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.inventoryResponseMapper = inventoryResponseMapper;
        this.inventoryVehicleResponseMapper = inventoryVehicleResponseMapper;
    }

    @Override
    public List<InventoryResponseModel> getInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();

        return inventoryResponseMapper.entityListToResponseModelList(inventoryList);
    }

    @Override
    public InventoryVehicleResponseModel getInventoryByInventoryId(String inventoryId) {
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);
        List<Vehicle> vehicleList = vehicleRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);

        return inventoryVehicleResponseMapper.entityToResponseModel(inventory, vehicleList);
    }

    @Override
    public InventoryResponseModel updateInventory(String inventoryId, InventoryRequestModel inventoryRequestModel) {
        Inventory foundInventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, foundInventory.getInventoryIdentifier());
        inventory.setId(foundInventory.getId());

        return inventoryResponseMapper.entityToResponseModel(inventory);
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {

        Inventory inventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel, new InventoryIdentifier());

        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(inventory));
    }

    @Override
    public void deleteInventory(String inventoryId) {
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier_InventoryId(inventoryId);

        inventoryRepository.delete(inventory);
    }
}
