package com.benzair.myimdb.directormovies;

import com.benzair.myimdb.presentationlayer.movies.MovieResponseDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data //Creates the getters and setters
@NoArgsConstructor
public class DirectorMoviesResponseDTO {

    // We are merging two response at once (the director and movie responses).

    private String directorId;
    private String name;
    private LocalDate dob;
    private String country;
    private String imageURL;
    private List<MovieResponseDTO> movies;
}
