package com.benzair.cardealershipws.salesandmarketingsubdomain.presentationlayer;

import com.benzair.cardealershipws.salesandmarketingsubdomain.businesslayer.SaleService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers/{customerId}/purchases")
public class CustomerPurchaseController {

    private final SaleService saleService;

    public CustomerPurchaseController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SaleResponseModel>> getAllPurchasesForCustomer(@PathVariable String customerId){
        return ResponseEntity.ok().body(saleService.getAllPurchasesForCustomer(customerId));
    }

    @GetMapping(value = "{saleId", produces = "application/json")
    public ResponseEntity<SaleResponseModel> getCustomerPurchaseBySaleId(@PathVariable String customerId, @PathVariable String saleId){
        return ResponseEntity.ok().body(saleService.getCustomerPurchaseBySaleId(customerId, saleId));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<SaleResponseModel> processCustomerPurchase(@RequestBody SaleRequestModel saleRequestModel, @PathVariable String customerId){
        return ResponseEntity.status(HttpStatusCode.CREATED).body(saleService.makeCustomerPurchase(saleRequestModel, customerId));
    }
}
