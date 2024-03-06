package com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.businesslayer;

import com.hichembenzair.soccerjerseysapplication.common.JerseyIdentifier;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer.Jersey;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.dataaccesslayer.JerseyRepository;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.mapperlayer.JerseyRequestMapper;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.mapperlayer.JerseyResponseMapper;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyRequestModel;
import com.hichembenzair.soccerjerseysapplication.jerseyssubdomain.presentationlayer.JerseyResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JerseyServiceImpl implements JerseyService {

    private final JerseyRepository jerseyRepository;
    private final JerseyResponseMapper jerseyResponseMapper;
    private final JerseyRequestMapper jerseyRequestMapper;

    public JerseyServiceImpl(JerseyRepository jerseyRepository, JerseyResponseMapper jerseyResponseMapper, JerseyRequestMapper jerseyRequestMapper) {
        this.jerseyRepository = jerseyRepository;
        this.jerseyResponseMapper = jerseyResponseMapper;
        this.jerseyRequestMapper = jerseyRequestMapper;
    }

    @Override
    public List<JerseyResponseModel> getJerseys(String jerseyId, String queryParams) {
        // Implement your logic to retrieve jerseys based on jerseyId and queryParams
        // For example:
        List<Jersey> jerseys = jerseyRepository.findAll(); // Replace with your query
        return jerseyResponseMapper.entityListToResponseModelList(jerseys);
    }

    @Override
    public JerseyResponseModel getJerseyById(String jerseyId) {
        // Implement your logic to retrieve a jersey by jerseyId
        Jersey jersey = jerseyRepository.findByJerseyIdentifier_JerseyId(jerseyId);
        if (jersey != null) {
            return jerseyResponseMapper.entityToResponseModel(jersey);
        } else {
            // Handle the case where jersey is not found
            return null;
        }
    }

    @Override
    public JerseyResponseModel updateJersey(String jerseyId, JerseyRequestModel jerseyRequestModel) {
        // Implement your logic to update a jersey
        Jersey existingJersey = jerseyRepository.findByJerseyIdentifier_JerseyId(jerseyId);
        if (existingJersey != null) {
            Jersey updatedJersey = jerseyRequestMapper.updateEntityFromRequestModel(jerseyRequestModel, existingJersey);
            return jerseyResponseMapper.entityToResponseModel(jerseyRepository.save(updatedJersey));
        } else {
            // Handle the case where jersey is not found
            return null;
        }
    }

    @Override
    public JerseyResponseModel addJersey(JerseyRequestModel jerseyRequestModel) {
        // Implement your logic to add a new jersey
        Jersey jersey = jerseyRequestMapper.requestModelToEntity(jerseyRequestModel, new JerseyIdentifier());
        return jerseyResponseMapper.entityToResponseModel(jerseyRepository.save(jersey));
    }

    @Override
    public void deleteJersey(String jerseyId) {
        // Implement your logic to delete a jersey
        Jersey jersey = jerseyRepository.findByJerseyIdentifier_JerseyId(jerseyId);
        if (jersey != null) {
            jerseyRepository.delete(jersey);
        } else {
            // Handle the case where jersey is not found
        }
    }
}
