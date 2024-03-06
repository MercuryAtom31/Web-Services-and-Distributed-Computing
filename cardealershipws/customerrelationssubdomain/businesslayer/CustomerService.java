package com.benzair.cardealershipws.customerrelationssubdomain.businesslayer;

import com.benzair.cardealershipws.customerrelationssubdomain.presentationlayer.CustomerRequestModel;
import com.benzair.cardealershipws.customerrelationssubdomain.presentationlayer.CustomerResponseModel;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseModel> getCustomers();
    CustomerResponseModel getCustomerByCustomerId(String customerId);
    CustomerResponseModel addCustomer(CustomerRequestModel customerRequestModel);
    CustomerResponseModel updateCustomer(CustomerRequestModel updatedCustomer, String customerId);
    void removeCustomer(String customerId);
}
