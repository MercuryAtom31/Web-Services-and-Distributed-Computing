package com.benzair.cardealershipws.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import com.benzair.cardealershipws.common.Currency;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Embeddable
@NoArgsConstructor
public class MSRP {

    private DecimalFormat value;

    @Enumerated(EnumType.STRING)
    private Currency msrpCurrency;
}
