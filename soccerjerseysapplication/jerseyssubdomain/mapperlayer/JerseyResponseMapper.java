package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.mapperlayer;

import com.hichembenzair.soccerjerseysapplication.common.JerseyIdentifier;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer.Jersey;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JerseyResponseMapper {

    @Mapping(target = "jerseyId", source = "jerseyId")
    @Mapping(target = "jerseyIdentifier", source = "jerseyIdentifier")
    JerseyResponseModel entityToResponseModel(Jersey jersey);

    Iterable<JerseyResponseModel> entityListToResponseModelList(Iterable<Jersey> jerseys);
}
