package com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseModel {

    private String customerId;
    private String customerFirstName;
    private String customerLastName;
}
