package com.benzair.myimdb.presentationlayer;

import com.benzair.myimdb.businesslayer.DirectorMoviesService;
import com.benzair.myimdb.directormovies.DirectorMoviesResponseDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/directors/{directorId}/movies")
public class DirectorMoviesController {

    private DirectorMoviesService directorMoviesService;

    public DirectorMoviesController(DirectorMoviesService directorMoviesService) {
        this.directorMoviesService = directorMoviesService;
    }

    @GetMapping()
    public DirectorMoviesResponseDTO getAllMoviesForADirector(@PathVariable String directorId){
        return directorMoviesService.getAllMoviesByDirectorId(directorId);
    }
}
