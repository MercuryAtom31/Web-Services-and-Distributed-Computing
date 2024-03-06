package com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeByEmployeeIdentifier(String employeeIdentifier);
}
