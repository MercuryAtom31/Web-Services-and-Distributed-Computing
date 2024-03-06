package com.benzair.cardealershipws.customerrelationssubdomain.presentationlayer;

import com.benzair.cardealershipws.customerrelationssubdomain.dataaccesslayer.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.mapstruct.Builder;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class CustomerResponseModel {

    String customerId;
    String firstName;
    String lastName;
    String emailAddress;
    String streetAddress;
    String city;
    String province;
    String country;
    String postalCode;
    List<PhoneNumber> phoneNumbers;
}
