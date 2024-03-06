package com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer;

import com.benzair.cardealershipws.common.Currency;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.FinancingAgreementsDetails;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.SaleStatus;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Warranty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestModel {

    private String inventoryId;
    private String vin;
    private String employeeId;
    private BigDecimal salePrice;
    private Currency currency;
    private SaleStatus saleStatus;
    private LocalDate saleOfferDate;
    private FinancingAgreementsDetails financingAgreementsDetails;
    private Warranty warranty;
}
