package com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.department;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.Department;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentResponseMapper {

    @Mapping(expression = "java(department.getDepartmentIdentifier().getDepartmentId())", target = "departmentId")
    DepartmentResponseModel entityToResponseModel(Department department);

    List<DepartmentResponseModel> entityListToResponseModelList(List<Department> departmentList);
}
