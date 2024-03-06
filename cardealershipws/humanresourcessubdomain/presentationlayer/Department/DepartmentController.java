package com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department;

import com.benzair.cardealershipws.humanresourcessubdomain.businesslayer.Department.DepartmentService;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.Department;
import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.DepartmentIdentifier;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments() {
        /*
        constructing the HTTP response. Let's break it down:

        ResponseEntity.ok():
        This creates a ResponseEntity with an HTTP status of 200 (OK).
        It indicates that the request has succeeded.

        .body(departmentService.getAllDepartments()):
        This sets the body of the response. The body is the data that will be sent
        in the response payload. In this case,
        it's the list of DepartmentResponseModel DTOs returned by
        departmentService.getAllDepartments().
        So, the entire line is creating an HTTP response with a status of OK
        and a body containing the list of DepartmentResponseModel DTOs,
        which will be sent back to the client (user who made the HTTP request).
         */
        return ResponseEntity.ok().body(departmentService.getAllDepartments());
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<DepartmentResponseModel> getDepartmentByDepartmentId(@PathVariable String departmentId) {
        return ResponseEntity.ok().body(departmentService.getDepartmentByDepartmentId(departmentId));
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseModel> addDepartment(@RequestBody DepartmentRequestModel departmentRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.addDepartment(departmentRequestModel));
    }
}
