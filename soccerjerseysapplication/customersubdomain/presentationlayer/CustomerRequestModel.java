package com.hichembenzair.soccerjerseysapplication.customersubdomain.presentationlayer;

import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerRequestModel {

    private String customerFirstName;
    private String customerLastName;
}
