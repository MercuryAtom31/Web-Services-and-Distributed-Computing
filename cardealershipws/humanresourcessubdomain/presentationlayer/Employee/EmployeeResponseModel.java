package com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Employee;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseModel {

    private String employeeId;

    private String streetAddress;
    private String city;
    private String province;
    private String Country;
    private String PostalCode;

    private List<PhoneNumber> phoneNumbers;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;
}
