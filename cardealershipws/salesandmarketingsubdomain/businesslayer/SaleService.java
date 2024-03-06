package com.benzair.cardealershipws.salesandmarketingsubdomain.businesslayer;

import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleRequestModel;
import com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer.SaleResponseModel;

import java.util.List;

public interface SaleService {

    SaleResponseModel getCustomerPurchaseBySaleId(String customerId, String saleId);

    List<SaleResponseModel> getAllPurchasesForCustomer(String customerId);

    SaleResponseModel makeCustomerPurchase(SaleRequestModel saleRequestModel, String saleId);
}
