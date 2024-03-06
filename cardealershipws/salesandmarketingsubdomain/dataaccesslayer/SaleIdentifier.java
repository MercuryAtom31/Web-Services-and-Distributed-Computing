package com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class SaleIdentifier {

    private String saleId;

    public SaleIdentifier() {
        this.saleId = UUID.randomUUID().toString();
    }
}
