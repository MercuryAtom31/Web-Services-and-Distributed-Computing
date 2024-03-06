package com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer;

import com.benzair.cardealershipws.common.Currency;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
