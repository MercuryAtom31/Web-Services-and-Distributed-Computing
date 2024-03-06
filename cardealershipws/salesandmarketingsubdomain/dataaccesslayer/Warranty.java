package com.benzair.cardealershipws.salesandmarketingsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Warranty {

    private LocalDate warrantyEndDate;
    private String warrantyTerms;
}
