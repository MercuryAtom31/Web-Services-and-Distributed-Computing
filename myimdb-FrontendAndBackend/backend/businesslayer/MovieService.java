package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.dataaccesslayer.Movie;
import com.benzair.myimdb.presentationlayer.movies.MovieRequestDTO;
import com.benzair.myimdb.presentationlayer.movies.MovieResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    List<MovieResponseDTO> getAllMovies();

    //Beginning of my code for getting a single movie item.

    MovieResponseDTO findMovieByMovieId(String movieId);
    //Movie getOneMovie(String movieId);

    //End of my code for getting a single movie item.

    MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO);

    MovieResponseDTO updateMovie(MovieRequestDTO movieRequestDTO, String movieId);

    void deleteMovie(String movieId);

}
