package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer;

import com.hichembenzair.soccerjerseysapplication.common.JerseyIdentifier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JerseyResponseModel {

    private Long jerseyId;

    private JerseyIdentifier jerseyIdentifier;

    private String size;

    private String color;

    private String styles;

    private Integer stockAmount;

    private Double price;
}
