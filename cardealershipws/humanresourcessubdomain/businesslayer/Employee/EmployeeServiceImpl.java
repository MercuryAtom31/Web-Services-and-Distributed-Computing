package com.benzair.cardealershipws.humanresourcessubdomain.businesslayer.Employee;

import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Employee.EmployeeResponseModel;
import com.benzair.cardealershipws.utils.exceptions.NotFoundException;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.Employee;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponseModel> getAllEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        List<EmployeeResponseModel> employeeResponseModels = new ArrayList<>();

        for (Employee employee : employeeList) {
            EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel();
            BeanUtils.copyProperties(employee, employeeResponseModel);

            employeeResponseModel.setEmployeeId(employee.getEmployeeIdentifier().getEmployeeId());
            employeeResponseModel.setStreetAddress(employee.getAddress().getStreetAddress());
            employeeResponseModel.setCity(employee.getAddress().getCity());
            employeeResponseModel.setProvince(employee.getAddress().getProvince());
            employeeResponseModel.setCountry(employee.getAddress().getCountry());
            employeeResponseModel.setPostalCode(employee.getAddress().getPostalCode());

            employeeResponseModels.add(employeeResponseModel);
        }
        return employeeResponseModels;
    }

}
