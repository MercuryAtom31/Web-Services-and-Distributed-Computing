package com.benzair.cardealershipws.humanresourcessubdomain.dataaccesslayer.employee;

import com.benzair.cardealershipws.common.EmployeeIdentifier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private EmployeeIdentifier employeeIdentifier;

    @Embedded
    private Address address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "employee_phonenumbers", joinColumns = @JoinColumn(name = "employee_id"))
    private List<PhoneNumber> phoneNumbers;

    private String firstName;
    private String lastName;
    private String emailAddress;
    private Double salary;
    private Double commissionRate;

    public Employee(@NotNull Address address,
                    @NotNull List<PhoneNumber> phoneNumbers,
                    @NotNull String firstName,
                    @NotNull String lastName,
                    @NotNull String emailAddress,
                    @NotNull Double salary,
                    @NotNull Double commissionRate) {
        this.id = id;
        this.employeeIdentifier = new EmployeeIdentifier();
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.salary = salary;
        this.commissionRate = commissionRate;
    }
}
