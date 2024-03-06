package com.benzair.cardealershipws.inventorymanagementsubdomain.presentationlayer.inventory;

import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseModel extends RepresentationModel<InventoryResponseModel> {

    private String inventoryId;

    private InventoryType type;
}
