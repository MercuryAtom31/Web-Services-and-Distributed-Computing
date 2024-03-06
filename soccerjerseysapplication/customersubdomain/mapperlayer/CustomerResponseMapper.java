package com.hichembenzair.soccerjerseysapplication.customersubdomain.mapperlayer;

import com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer.Customer;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerResponseModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {
    CustomerResponseModel entityToResponseModel(Customer customer);
}
