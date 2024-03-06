package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.mapperlayer;

import com.hichembenzair.soccerjerseysapplication.common.JerseyIdentifier;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer.Jersey;

import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JerseyRequestMapper {

    @Mapping(target = "jerseyId", ignore = true)
    @Mapping(target = "jerseyIdentifier", source = "jerseyIdentifier")
    Jersey requestModelToEntity(JerseyRequestModel requestModel, JerseyIdentifier jerseyIdentifier);
}
