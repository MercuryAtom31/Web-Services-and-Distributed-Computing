package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.businesslayer;

import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer.Jersey;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyRequestModel;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyResponseModel;

import java.util.List;

public interface JerseyService {

    List<JerseyResponseModel> getJerseys(String jerseyId, String queryParams);
    JerseyResponseModel getJerseyById(String jerseyId);
    JerseyResponseModel updateJersey(String jerseyId, JerseyRequestModel jerseyRequestModel);
    JerseyResponseModel addJersey(JerseyRequestModel jerseyRequestModel);
    void deleteJersey(String jerseyId);
}
