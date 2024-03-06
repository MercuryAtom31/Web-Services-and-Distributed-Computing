package com.hichembenzair.soccerjerseysapplication.customersubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    The JpaRepository<Customer, Long> provides basic CRUD methods
    such as save, findById, findAll, deleteById, etc.
     */

    /*
    findByCustomerIdentifier_CustomerIdentifier is telling Spring Data JPA
    that you want to find a Customer based on the customerIdentifier property,
    and you'll provide the specific customerIdentifier value when you call the method.
     */
    Customer findByCustomerIdentifier_CustomerIdentifier(String customerIdentifier);

    Customer findByCustomerFirstNameAndCustomerLastName(String customerFirstName, String customerLastName);

    Customer findByCustomerIdentifier_CustomerId(String customerId);
}
