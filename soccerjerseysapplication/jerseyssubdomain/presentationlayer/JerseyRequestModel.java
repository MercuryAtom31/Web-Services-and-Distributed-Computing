package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JerseyRequestModel {

    @NotNull
    private String size;

    @NotNull
    private String color;

    @NotNull
    private String styles;

    @NotNull
    private Integer stockAmount;

    @NotNull
    private Double price;
}
