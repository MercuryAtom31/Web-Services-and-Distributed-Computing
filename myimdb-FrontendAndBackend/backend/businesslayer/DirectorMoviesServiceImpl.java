package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.dataaccesslayer.Director;
import com.benzair.myimdb.dataaccesslayer.DirectorRepository;
import com.benzair.myimdb.dataaccesslayer.Movie;
import com.benzair.myimdb.dataaccesslayer.MovieRepository;
import com.benzair.myimdb.directormovies.DirectorMoviesResponseDTO;
import com.benzair.myimdb.presentationlayer.directors.DirectorResponseDTO;
import com.benzair.myimdb.presentationlayer.movies.MovieResponseDTO;
import com.benzair.myimdb.utils.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectorMoviesServiceImpl implements DirectorMoviesService{

    private DirectorRepository directorRepository;
    private MovieRepository movieRepository;

    public DirectorMoviesServiceImpl(DirectorRepository directorRepository, MovieRepository movieRepository) {
        this.directorRepository = directorRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public DirectorMoviesResponseDTO getAllMoviesByDirectorId(String directorId) {

        Director foundDirector = directorRepository.findDirectorByDirectorId(directorId);

        if(foundDirector == null){
            throw new NotFoundException("Unknown directorId: " + directorId);
        }

        DirectorMoviesResponseDTO directorMoviesResponseDTO = new DirectorMoviesResponseDTO();
        BeanUtils.copyProperties(foundDirector, directorMoviesResponseDTO);

        List<Movie> moviesList = movieRepository.findMoviesByDirector_DirectorId(directorId);

        List<MovieResponseDTO> movieResponseDTOList = new ArrayList<>();

        for (Movie movie : moviesList) {
            MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
            BeanUtils.copyProperties(movie, movieResponseDTO);

            DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
            BeanUtils.copyProperties(movie.getDirector(), directorResponseDTO);
            movieResponseDTO.setDirector(directorResponseDTO);
            movieResponseDTOList.add(movieResponseDTO);
        }

        directorMoviesResponseDTO.setMovies(movieResponseDTOList);

        return directorMoviesResponseDTO;
    }
}
