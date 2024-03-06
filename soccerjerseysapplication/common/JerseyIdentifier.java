package com.hichembenzair.soccerjerseysapplication.common;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Getter
@Embeddable
public class JerseyIdentifier {

    private String jerseyId;

    public JerseyIdentifier() {
        this.jerseyId = UUID.randomUUID().toString();
    }

    public JerseyIdentifier(String jerseyId) {
        this.jerseyId = jerseyId;
    }
}
