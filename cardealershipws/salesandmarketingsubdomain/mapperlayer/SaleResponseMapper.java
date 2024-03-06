package com.benzair.cardealershipws.salesandmarketingsubdomain.mapperlayer;

import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.Customer;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Sale;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.CustomerPurchaseController;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleResponseModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface SaleResponseMapper {

    @Mapping(expression = "java(sale.getSalePrice().getAmount())", target = "salePrice")
    @Mapping(expression = "java(sale.getSalePrice().getCurrency())", target = "currency")
    @Mapping(expression = "java(sale.getSaleIdentifier().getSaleId())", target = "saleId")
    @Mapping(expression = "java(sale.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(sale.getVehicleIdentifier().getVin())", target = "vin")
    @Mapping(expression = "java(vehicle.getMake())", target = "vehicleMake")
    @Mapping(expression = "java(vehicle.getModel())", target = "vehicleModel")
    @Mapping(expression = "java(sale.getCustomerIdentifier().getCustomerId())", target = "customerId")
    @Mapping(expression = "java(customer.getFirstName())", target = "customerFirstName")
    @Mapping(expression = "java(customer.getLastName())", target = "customerLastName")
    @Mapping(expression = "java(sale.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    @Mapping(expression = "java(employee.getFirstName())", target = "employeeFirstName")
    @Mapping(expression = "java(employee.getLastName())", target = "employeeLastName")
    SaleResponseModel entityToResponseModel(Sale sale, Customer customer, Employee employee, Vehicle vehicle);

    @AfterMapping
    default void addLinks(@MappingTarget SaleResponseModel model, Sale sale, Customer customer, Employee employee, Vehicle vehicle){

        //self link
        Link selfLink = linkTo(methodOn(CustomerPurchaseController.class)
                .getCustomerPurchaseBySaleId(model.getCustomerId(), model.getSaleId()))
                .withSelfRel();
        model.add(selfLink);

        //All purchases for customer.
        Link purchasesLink =
                linkTo(methodOn(CustomerPurchaseController.class)
                        .getAllPurchasesForCustomer(model.getCustomerId()))
                        .withRel("all purchases");
        model.add(purchasesLink);

        //Vehicle link.
        Link vehicleLink=
                linkTo(methodOn(InventoryVehicleController.class)
                        .getVehicleInInventoryByVIN(vehicle.getInventoryIdentifier().getInventoryId(), model.getVin()))
                        .withRel("Vehicle");
        model.add(vehicleLink);
    }


}
