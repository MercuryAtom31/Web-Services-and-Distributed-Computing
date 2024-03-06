package com.benzair.cardealershipws.humanresourcessubdomain.businesslayer.Department;

import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentRequestModel;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentResponseModel;

import java.util.List;

public interface DepartmentService {

    List<DepartmentResponseModel> getAllDepartments();
    DepartmentResponseModel getDepartmentByDepartmentId(String departmentId);
    DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel);
}
