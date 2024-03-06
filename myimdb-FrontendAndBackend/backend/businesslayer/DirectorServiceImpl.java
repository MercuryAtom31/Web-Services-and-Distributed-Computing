package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.dataaccesslayer.Director;
import com.benzair.myimdb.dataaccesslayer.DirectorRepository;
import com.benzair.myimdb.utils.exceptions.InUseException;
import com.benzair.myimdb.utils.exceptions.NotFoundException;
import com.benzair.myimdb.presentationlayer.directors.DirectorRequestDTO;
import com.benzair.myimdb.presentationlayer.directors.DirectorResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DirectorServiceImpl implements DirectorService{


    private DirectorRepository directorRepository;


    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<DirectorResponseDTO> getAllDirectors() {

        List<Director> directorEntities = directorRepository.findAll();

        List<DirectorResponseDTO> directorResponseDTOList = new ArrayList<>();

        for (Director director : directorEntities) {
            DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
            BeanUtils.copyProperties(director, directorResponseDTO);
            directorResponseDTOList.add(directorResponseDTO);
        }
        return directorResponseDTOList;
    }

    @Override
    public DirectorResponseDTO findDirectorByDirectorId(String directorId) {

        Director foundDirector = directorRepository.findDirectorByDirectorId(directorId);

        if(foundDirector == null){
            throw new NotFoundException("Unknown directorId: " + directorId);
        }

        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        BeanUtils.copyProperties(foundDirector, directorResponseDTO);

        return directorResponseDTO;
    }

    @Override
    public DirectorResponseDTO addDirector(DirectorRequestDTO directorRequestDTO) {

        Director director = new Director();
        /*
        BeanUtils.copyProperties(directorRequestDTO, director);
        Here, the method copies the properties from the directorRequestDTO (which likely contains data sent in a request)
        to the director object. This is a way to map data from the request to a Director entity.
         */
        BeanUtils.copyProperties(directorRequestDTO, director);
        /*
        The line director.setDirectorId(UUID.randomUUID().toString());
        is generating a unique identifier (UUID) for the director object
        and setting it as the value for the directorId property of that object.

        This is usually done to ensure that each director added to the system has a unique identifier.
         */
        director.setDirectorId(UUID.randomUUID().toString());
        /*
        Director savedDirector = directorRepository.save(director);
        The director object, with its properties set, is saved to the database using
        the directorRepository.save() method.
        This persists the director's information in the data store.
         */
        Director savedDirector = directorRepository.save(director);
        /*
        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        Here, a new DirectorResponseDTO object is created.
        This likely represents a DTO (Data Transfer Object) for sending data back in a response.
         */
        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        /*
        BeanUtils.copyProperties(savedDirector, directorResponseDTO);
        The method copies the properties from the savedDirector (which now includes any database-generated values)
        to the directorResponseDTO object. This maps the data from the database entity to a response DTO.
         */
        BeanUtils.copyProperties(savedDirector, directorResponseDTO);
        /*
        return directorResponseDTO;
        Finally, the method returns the directorResponseDTO,
        which is the result of adding the director to the system
        and provides a representation of the added director suitable for a response.
         */
        return directorResponseDTO;
        //Summary.
        /*
        In summary, this method takes data from a directorRequestDTO,
        creates a Director entity,
        assigns it a unique identifier,
        saves it to the database,
        maps the saved entity to a DirectorResponseDTO,
        and returns this DTO as the response.
        It essentially adds a director to the system and responds with information about the added director.
         */

//        try {
//            Director director = new Director();
//            BeanUtils.copyProperties(directorRequestDTO, director);
//            director.setDirectorId(UUID.randomUUID().toString());
//
//            Director savedDirector = directorRepository.save(director);
//
//            DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
//            BeanUtils.copyProperties(savedDirector, directorResponseDTO);
//
//            return directorResponseDTO;
//        } catch (DataIntegrityViolationException e) {
//            // Handle data integrity violation (e.g., unique constraint violation)
//            throw new DirectorDataIntegrityException("Data integrity violation when adding director", e);
//        } catch (ValidationException e) {
//            // Handle validation failure
//            throw new DirectorValidationException("Director validation failed", e);
//        }
    }

    @Override
    public DirectorResponseDTO updateDirector(DirectorRequestDTO directorRequestDTO, String directorId) {

        Director foundDirector = directorRepository.findDirectorByDirectorId(directorId);

        if (foundDirector == null){
            //throw exception.
            throw new NotFoundException("Unknown movieId provided: " + directorId);
        }
        Director director = new Director();
        BeanUtils.copyProperties(directorRequestDTO, director);
        //Can throw an exception.
        director.setDirectorId(foundDirector.getDirectorId());
        director.setId(foundDirector.getId());

        //Save director entity to directory repository.
        Director savedDirector = directorRepository.save(director);

        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        BeanUtils.copyProperties(savedDirector, directorResponseDTO);

        return directorResponseDTO;
    }

    @Override
    public void deleteDirector(String directorId) {

        Director foundDirector = directorRepository.findDirectorByDirectorId(directorId);

        if (foundDirector == null){
            throw new NotFoundException("Unknown director is: " + directorId);
        }

        try{
            directorRepository.delete(foundDirector);
        } catch (DataIntegrityViolationException ex) {
            throw new InUseException("Cannot delete director with directorId: " + directorId +
                    " as it currently assigned to one or more movies.");
        }

        // directorRepository.delete(foundDirector);
    }


}
