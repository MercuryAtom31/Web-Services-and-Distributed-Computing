package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.dataaccesslayer.Director;
import com.benzair.myimdb.dataaccesslayer.Movie;
import com.benzair.myimdb.presentationlayer.directors.DirectorRequestDTO;
import com.benzair.myimdb.presentationlayer.directors.DirectorResponseDTO;

import java.util.List;

public interface DirectorService {

    List<DirectorResponseDTO> getAllDirectors();

    //Director findDirectorByDirectorId(String directorId);
    DirectorResponseDTO findDirectorByDirectorId(String directorId);

    DirectorResponseDTO addDirector(DirectorRequestDTO directorRequestDTO);

    DirectorResponseDTO updateDirector(DirectorRequestDTO directorRequestDTO, String directorId);

    void deleteDirector(String directorId);
}
