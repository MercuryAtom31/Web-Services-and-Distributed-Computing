package com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory;

import com.benzair.cardealershipws.common.InventoryIdentifier;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {

    @Mapping(target = "id", ignore = true)
    Inventory requestModelToEntity(InventoryRequestModel inventoryRequestModel, InventoryIdentifier inventoryIdentifier);
}
