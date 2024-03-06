package com.benzair.myimdb.businesslayer;

import com.benzair.myimdb.dataaccesslayer.Director;
import com.benzair.myimdb.dataaccesslayer.DirectorRepository;
import com.benzair.myimdb.dataaccesslayer.Movie;
import com.benzair.myimdb.dataaccesslayer.MovieRepository;
import com.benzair.myimdb.presentationlayer.directors.DirectorResponseDTO;
import com.benzair.myimdb.utils.exceptions.NotFoundException;
import com.benzair.myimdb.presentationlayer.movies.MovieRequestDTO;
import com.benzair.myimdb.presentationlayer.movies.MovieResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service // Is more specific than @Component. @Service is a type of component.
public class MovieServiceImpl implements MovieService{


    private MovieRepository movieRepository;
    private DirectorRepository directorRepository;

    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
    }

    //    public MovieServiceImpl(MovieRepository movieRepository) {
//        this.movieRepository = movieRepository;
//    }

    @Override
    public List<MovieResponseDTO> getAllMovies() {

        List<Movie> movieEntities = movieRepository.findAll();
        //Convert list of Movie entities to list of MovieResponseDTO.
        List<MovieResponseDTO> movieResponseDTOList = new ArrayList<>();

        for (Movie movie : movieEntities) {
            MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
            BeanUtils.copyProperties(movie, movieResponseDTO);

            //We took the director from the...
            DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
            BeanUtils.copyProperties(movie.getDirector(), directorResponseDTO);
            movieResponseDTO.setDirector(directorResponseDTO);


            movieResponseDTOList.add(movieResponseDTO);
        }

        return movieResponseDTOList;
    }


    //Beginning of my code for getting a single movie item.

    @Override
    public MovieResponseDTO findMovieByMovieId(String movieId) {
        //return movieRepository.findMovieByMovieId("2");
        //return movieRepository.findMovieByMovieId(movieId);
        Movie foundMovie = movieRepository.findMovieByMovieId(movieId);

        if(foundMovie == null) {
            throw new NotFoundException("Unknown movieId: " + movieId);
        }
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        BeanUtils.copyProperties(foundMovie, movieResponseDTO);
        //We took the director from the...
        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        BeanUtils.copyProperties(foundMovie.getDirector(), directorResponseDTO);
        movieResponseDTO.setDirector(directorResponseDTO);

        return movieResponseDTO;
    }

    //End of my code for getting a single movie item.


    @Override
    public MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {

        //find director by provided directorId.
        Director foundDirector = directorRepository.findDirectorByDirectorId(movieRequestDTO.getDirectorId());

        if(foundDirector == null){
            throw new NotFoundException("Unknown directorId: " + movieRequestDTO.getDirectorId());
        }

        //Convert movieRequestDTO to an Entity.
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieRequestDTO, movie);
        movie.setMovieId(UUID.randomUUID().toString());
        //Add the director to the movie.
        movie.setDirector(foundDirector);

        //Save movie entity to database via repository.
        Movie savedMovie = movieRepository.save(movie);

        //Convert savedMovie (Entity) to MovieResponseDTO.
        MovieResponseDTO movieResponseDTO  = new MovieResponseDTO();
        BeanUtils.copyProperties(savedMovie, movieResponseDTO);

        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        BeanUtils.copyProperties(savedMovie.getDirector(), directorResponseDTO);
        movieResponseDTO.setDirector(directorResponseDTO);

        return movieResponseDTO;
    }

    @Override
    public MovieResponseDTO updateMovie(MovieRequestDTO movieRequestDTO, String movieId) {

        Movie foundMovie = movieRepository.findMovieByMovieId(movieId);

        if (foundMovie == null){
            //throw exception.
            throw new NotFoundException("Unknown movieId provided: " + movieId);
        }
        //Convert movieRequestDTO to an Entity.
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieRequestDTO, movie);
        movie.setMovieId(foundMovie.getMovieId());
        movie.setId(foundMovie.getId());

        //find director by provided directorId.
        Director foundDirector = directorRepository.findDirectorByDirectorId(movieRequestDTO.getDirectorId());
        movie.setDirector(foundDirector);

        //Save movie entity to movie repository.
        Movie savedMovie = movieRepository.save(movie);

        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        BeanUtils.copyProperties(foundDirector, directorResponseDTO);

        //Convert savedMovie (Entity) to MovieResponseDTO.
        MovieResponseDTO movieResponseDTO  = new MovieResponseDTO();
//        BeanUtils.copyProperties(savedMovie, movieResponseDTO);

        movieResponseDTO.setDirector(directorResponseDTO);
        BeanUtils.copyProperties(savedMovie, movieResponseDTO);

        return movieResponseDTO;
    }

    @Override
    public void deleteMovie(String movieId) {
        Movie foundMovie = movieRepository.findMovieByMovieId(movieId);

        if (foundMovie == null){
            //throw exception.
            throw new NotFoundException("Unknown movieId provided: " + movieId);
        }

        movieRepository.delete(foundMovie);
    }
}
