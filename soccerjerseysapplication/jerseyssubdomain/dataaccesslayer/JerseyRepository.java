package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JerseyRepository extends JpaRepository<Jersey, Long> {

    // Find a jersey by its identifier
    Jersey findJerseyByJerseyIdentifier(String jerseyIdentifier);

    // Find jerseys by color
    List<Jersey> findJerseysByColor(String color);

    // Find jerseys by size and style
    List<Jersey> findJerseysBySizeAndStyles(String size, String styles);

    // Find jerseys with stock amount greater than a given value
    List<Jersey> findJerseysByStockAmountGreaterThan(Integer stockAmount);

    Jersey findByJerseyIdentifier_JerseyId(String jerseyId);
}
