package com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Options {

    private String name;

    private String description;

    @Embedded
    private Price price;

    public Options(@NotNull String name, @NotNull String description, @NotNull Price price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
