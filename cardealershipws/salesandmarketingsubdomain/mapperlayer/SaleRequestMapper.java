package com.benzair.cardealershipws.salesandmarketingsubdomain.mapperlayer;

import com.benzair.cardealershipws.common.CustomerIdentifier;
import com.benzair.cardealershipws.common.EmployeeIdentifier;
import com.benzair.cardealershipws.common.InventoryIdentifier;
import com.benzair.cardealershipws.common.VehicleIdentifier;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Price;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Sale;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.SaleIdentifier;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleRequestMapper {

    @Mapping(expression = "java(price)", target = "salePrice")
    Sale requestModelToEntity(SaleRequestModel saleRequestModel,
                              SaleIdentifier saleIdentifier,
                              InventoryIdentifier inventoryIdentifier,
                              VehicleIdentifier vehicleIdentifier,
                              EmployeeIdentifier employeeIdentifier,
                              CustomerIdentifier customerIdentifier,
                              Price price);
}
