package com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

    Sale findSaleByCustomerIdentifier_CustomerIdAndSaleIdentifier_SaleId(String customerId, String saleId);

    List<Sale> findSalesByCustomerIdentifier_CustomerId(String customerId);
}
