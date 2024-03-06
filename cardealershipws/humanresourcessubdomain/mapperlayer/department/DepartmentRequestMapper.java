package com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.department;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.Department;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.DepartmentIdentifier;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentRequestMapper {

    @Mapping(target = "id", ignore = true)
    Department requestModelToEntity(DepartmentRequestModel departmentRequestModel,
                                    DepartmentIdentifier departmentIdentifier);
}
