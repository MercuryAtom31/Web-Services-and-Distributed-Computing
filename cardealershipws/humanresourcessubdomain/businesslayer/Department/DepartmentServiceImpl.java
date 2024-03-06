package com.benzair.cardealershipws.humanresourcessubdomain.businesslayer.Department;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.Department;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.DepartmentIdentifier;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.DepartmentRepository;
import com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.department.DepartmentRequestMapper;
import com.benzair.cardealershipws.humanresourcessubdomain.mapperlayer.department.DepartmentResponseMapper;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentRequestModel;
import com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department.DepartmentResponseModel;
import com.benzair.cardealershipws.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private final DepartmentRequestMapper departmentRequestMapper;
    private final DepartmentResponseMapper departmentResponseMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentRequestMapper departmentRequestMapper, DepartmentResponseMapper departmentResponseMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentRequestMapper = departmentRequestMapper;
        this.departmentResponseMapper = departmentResponseMapper;
    }

    @Override
    public List<DepartmentResponseModel> getAllDepartments() {
        List<Department> departmentList = departmentRepository.findAll();
/*
        List<DepartmentResponseModel> departmentResponseModels = new ArrayList<>();

        for (Department department : departmentList) {
            DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
            BeanUtils.copyProperties(department, departmentResponseModel);

            departmentResponseModel.setDepartmentId(department.getDepartmentIdentifier().getDepartmentId());

            departmentResponseModels.add(departmentResponseModel);
        }

 */
        return departmentResponseMapper.entityListToResponseModelList(departmentList);
    }

    @Override
    public DepartmentResponseModel getDepartmentByDepartmentId(String departmentId) {
        Department foundDepartment = departmentRepository.findDepartmentByDepartmentIdentifier_DepartmentId(departmentId);

        if(foundDepartment == null) {
            throw new NotFoundException("Unknown departmentId: " + departmentId);
        }
/*
        DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
        BeanUtils.copyProperties(foundDepartment, departmentResponseModel);
        departmentResponseModel.setDepartmentId(foundDepartment.getDepartmentIdentifier().getDepartmentId());
*/
        return departmentResponseMapper.entityToResponseModel(foundDepartment);
    }

    @Override
    public DepartmentResponseModel addDepartment(DepartmentRequestModel departmentRequestModel) {

//        Department department = new Department();
//        BeanUtils.copyProperties(departmentRequestModel, department);
//        department.setDepartmentIdentifier(new DepartmentIdentifier());


        Department department = departmentRequestMapper.requestModelToEntity(departmentRequestModel,
                new DepartmentIdentifier());
        //Department savedDepartment = departmentRepository.save(department);

//        DepartmentResponseModel departmentResponseModel = new DepartmentResponseModel();
//        BeanUtils.copyProperties(savedDepartment, departmentResponseModel);
//        departmentResponseModel.setDepartmentId(savedDepartment.getDepartmentIdentifier().getDepartmentId());

        return departmentResponseMapper.entityToResponseModel(departmentRepository.save(department));
    }
}
