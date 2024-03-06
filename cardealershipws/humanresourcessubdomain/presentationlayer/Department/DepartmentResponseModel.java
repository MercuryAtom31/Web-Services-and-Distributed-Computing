package com.benzair.cardealershipws.humanresourcessubdomain.presentationlayer.Department;

import com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.department.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponseModel {

    private String departmentId;
    private String name;
    private Integer headCount;
    private List<Position> positions;
}

/*
So this is considered as a DTO (DepartmentResponseDTO)?

@GetMapping
    public ResponseEntity<List<DepartmentResponseModel>> getAllDepartments() {
        return ResponseEntity.ok().body(departmentService.getAllDepartments());
    }
    Since this controller method inside the
 */