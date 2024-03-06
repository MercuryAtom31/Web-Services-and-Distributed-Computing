package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.directormovies.DirectorMoviesResponseDTO;

public interface DirectorMoviesService {

    DirectorMoviesResponseDTO getAllMoviesByDirectorId(String directorId);
}
