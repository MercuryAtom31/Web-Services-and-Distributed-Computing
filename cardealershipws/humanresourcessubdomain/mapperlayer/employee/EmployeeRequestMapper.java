package com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.employee;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Employee.EmployeeRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {

    @Mapping(target = "id", ignore = true)
    // @Mapping(target = "employeeIdentifier", ignore = true) // You may need to customize this based on your requirements
    Employee requestModelToEntity(EmployeeRequestModel employeeRequestModel);
}
