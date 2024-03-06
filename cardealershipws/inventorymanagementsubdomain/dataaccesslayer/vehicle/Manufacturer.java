package com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Manufacturer {

    private String manufacturerName;

    private String manufacturerCountry;

    public Manufacturer(@NotNull String name, @NotNull String country) {
        this.manufacturerName = name;
        this.manufacturerCountry = country;
    }
}
