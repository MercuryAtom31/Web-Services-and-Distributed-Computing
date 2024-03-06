package com.hichembenzair.soccerjerseysapplication.customersubdomain.businesslayer;

import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerRequestModel;
import com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer.CustomerResponseModel;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseModel> getAllCustomers();

    CustomerResponseModel getCustomerByCustomerId(String customerId);

    CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel);

    CustomerResponseModel updateCustomer(CustomerRequestModel customerRequestModel, String customerId);

    void removeCustomer(String customerId);
}
