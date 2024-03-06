package com.benzair.cardealershipws.humanresourcessubdomain.businesslayer.Employee;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Employee.EmployeeResponseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponseModel> getAllEmployees();
}
