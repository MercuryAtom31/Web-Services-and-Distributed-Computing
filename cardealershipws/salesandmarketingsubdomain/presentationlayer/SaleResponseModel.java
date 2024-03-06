package com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer;

import com.benzair.cardealershipws.common.Currency;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.FinancingAgreementsDetails;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.SaleStatus;
import com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer.Warranty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseModel  extends RepresentationModel<SaleResponseModel> {

//    private String saleId;
//    private String inventoryId;
//    private String vin;
//    private String vehicleMake;
//    private String vehicleModel;
//    private String employeeId;
//    private String customerFirstName;
//    private String employeeLastName;
//    private String customerFirstname;
//    private String customerLastName;
//    private BigDecimal salePrice;
//    private Currency currency;
//    private SaleStatus saleStatus;
//    private LocalDate saleOfferDate;
//    private FinancingAgreementsDetails financingAgreementsDetails;
//    private Warranty warranty;
    private String saleId;
    private String inventoryId;
    private String vin;
    private String vehicleMake;
    private String vehicleModel;
    private String employeeId;
    private String customerFirstName;  // Adjusted from customerFirstname
    private String customerLastName;   // Adjusted from customerLastname
    private String employeeLastName;   // Adjusted from employeeLastname
    private BigDecimal salePrice;
    private Currency currency;
    private SaleStatus saleStatus;
    private LocalDate saleOfferDate;
    private FinancingAgreementsDetails financingAgreementsDetails;
    private Warranty warranty;
}
