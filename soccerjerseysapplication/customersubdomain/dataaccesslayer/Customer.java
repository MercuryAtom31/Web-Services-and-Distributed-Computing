package com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer;

import com.hichembenzair.soccerjerseysapplication.common.CustomerIdentifier;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Embedded
    private CustomerIdentifier customerIdentifier;

    private String customerFirstName;
    private String customerLastName;

    public Customer(@NotNull Long customerId, @NotNull CustomerIdentifier customerIdentifier,
                    @NotNull String customerFirstName, @NotNull String customerLastName) {
        this.customerId = customerId;
        this.customerIdentifier = new CustomerIdentifier();
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
    }
}
