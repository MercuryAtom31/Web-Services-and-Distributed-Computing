package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer;

import com.hichembenzair.soccerjerseysapplication.common.JerseyIdentifier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jerseys")
@NoArgsConstructor
@Data
public class Jersey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jerseyId;

    @Embedded
    private JerseyIdentifier jerseyIdentifier;
    private String size;
    private String color;
    private String styles; // short sleeves or long sleeves.
    private Integer stockAmount;
    private Double price;

    public Jersey(@NotNull JerseyIdentifier jerseyIdentifier,
                  @NotNull String size, @NotNull String color, @NotNull String styles,
                  @NotNull Integer stockAmount, @NotNull Double price) {
        this.jerseyIdentifier = jerseyIdentifier;
        this.size = size;
        this.color = color;
        this.styles = styles;
        this.stockAmount = stockAmount;
        this.price = price;
    }
}
