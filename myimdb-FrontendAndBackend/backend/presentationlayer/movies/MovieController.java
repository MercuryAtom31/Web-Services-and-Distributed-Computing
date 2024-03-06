package com.benzair.myimdb.presentationlayer.movies;

import com.benzair.myimdb.businesslayer.MovieService;
import com.benzair.myimdb.dataaccesslayer.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/movies")//This means that it is the first version of this endpoint.
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping//Means in order for this method to be
    public ResponseEntity<List<MovieResponseDTO>> getMovies(){
        List<MovieResponseDTO> movies = movieService.getAllMovies();
        //return movieService.getAllMovies();
        if (!movies.isEmpty()) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.noContent().build(); // Or return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{movieId}") // Define a new endpoint for getting a single movie
    public ResponseEntity<MovieResponseDTO> getMovieById(@PathVariable String movieId) {

        MovieResponseDTO movieResponseDTO = movieService.findMovieByMovieId(movieId);
        if (movieResponseDTO != null) {
            return ResponseEntity.ok(movieResponseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieRequestDTO movieRequestDTO){
        MovieResponseDTO createdMovie = movieService.addMovie(movieRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieResponseDTO> updateMovie(
            @RequestBody MovieRequestDTO movieRequestDTO,
            @PathVariable String movieId){
        //return movieService.updateMovie(movieRequestDTO, movieId);
        MovieResponseDTO updatedMovie = movieService.updateMovie(movieRequestDTO, movieId);
        if (updatedMovie != null) {
            return ResponseEntity.ok(updatedMovie); // Use HttpStatus.OK if appropriate
        } else {
            return ResponseEntity.notFound().build();
        }
        //return movieService.updateMovie(movieRequestDTO, movieId);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.noContent().build();
       // return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
