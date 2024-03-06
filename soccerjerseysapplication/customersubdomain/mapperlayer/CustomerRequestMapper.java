package com.hichembenzair.soccerjerseysapplication.customersubdomain.mapperlayer;

import com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer.Customer;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    @Mapping(target = "customerFirstName", source = "requestModel.customerFirstName")
    @Mapping(target = "customerLastName", source = "requestModel.customerLastName")
    Customer toEntity(CustomerRequestModel requestModel);

    Customer requestModelToEntity(CustomerRequestModel customerRequestModel);
}
