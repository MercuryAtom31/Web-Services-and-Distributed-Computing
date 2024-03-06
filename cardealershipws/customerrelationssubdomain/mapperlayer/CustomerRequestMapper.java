package com.benzair.cardealershipws.customerrelationssubdomain.mapperlayer;

import com.benzair.cardealershipws.common.CustomerIdentifier;
import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.Address;
import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.Customer;
import com.benzair.cardealershipws.customerrelationssubdomain.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Customer requestModelToEntity(CustomerRequestModel customerRequestModel, CustomerIdentifier customerIdentifier,
                                  Address address);

    default List<PhoneNumber> mapPhoneNumbers(List<String> phoneNumbers) {
        return phoneNumbers.stream()
                .map(PhoneNumber::new)
                .collect(Collectors.toList());
    }
}
