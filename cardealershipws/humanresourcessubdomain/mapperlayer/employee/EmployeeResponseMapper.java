package com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.employee;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Employee.EmployeeResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {

    @Mapping(expression = "java(employee.getEmployeeIdentifier().getEmployeeId())", target = "employeeId")
    EmployeeResponseModel entityToResponseModel(Employee employee);

    List<EmployeeResponseModel> entityListToResponseModelList(List<Employee> employeeList);
}
