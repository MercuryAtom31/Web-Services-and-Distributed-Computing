package com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Department findDepartmentByDepartmentIdentifier_DepartmentId(String departmentId);
}
