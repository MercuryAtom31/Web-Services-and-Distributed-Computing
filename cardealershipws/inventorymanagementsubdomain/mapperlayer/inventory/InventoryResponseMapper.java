package com.benzair.cardealershipws.inventorymanagementsubdomain.mapperlayer.inventory;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryController;
import com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory.InventoryResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface InventoryResponseMapper {

    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    InventoryResponseModel entityToResponseModel(Inventory inventory);

    List<InventoryResponseModel> entityListToResponseModelList(List<Inventory> inventoryList);

    @AfterMapping
    default void AddLinks(@MappingTarget InventoryResponseModel model, Inventory inventory){
        //self link
        Link selfLink = linkTo(methodOn(InventoryController.class).
                getInventoryByInventoryId(model.getInventoryId())).
                withSelfRel();
        model.add(selfLink);

        //all inventories link
        Link inventoriesLink = linkTo(methodOn(InventoryController.class).getInventories())
                .withRel("inventories");
        model.add(inventoriesLink);
    }
}
