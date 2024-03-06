package com.benzair.cardealershipws.customerrelationssubdomain.mapperlayer;

import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.Customer;
import com.benzair.cardealershipws.customerrelationssubdomain.presentationlayer.CustomerResponseModel;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {

    @Mapping(expression = "java(customer.getCustomerIdentifier().getCustomerId())", target = "customerId")
    @Mapping(expression = "java(customer.getAddress().getStreetAddress())", target = "streetAddress")
    @Mapping(expression = "java(customer.getAddress().getCity())", target = "city")
    @Mapping(expression = "java(customer.getAddress().getProvince())", target = "province")
    @Mapping(expression = "java(customer.getAddress().getCountry())", target = "country")
    @Mapping(expression = "java(customer.getAddress().getPostalCode())", target = "postalCode")
    CustomerResponseModel entityToResponseModel(Customer customer);

    List<CustomerResponseModel> entityListToResponseModelList(List<Customer> customers);

    default List<String> mapPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        return phoneNumbers.stream()
                .map(PhoneNumber::getPhoneNumber)
                .collect(Collectors.toList());
    }
}
