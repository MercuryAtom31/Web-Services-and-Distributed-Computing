package com.benzair.myimdb.presentationlayer.movies;

import com.benzair.myimdb.presentationlayer.directors.DirectorResponseDTO;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Creates the setters and getters.
@NoArgsConstructor
public class MovieResponseDTO {

    private String movieId; // public id.
    private String title;
    private Integer releaseYear;
    private String posterURL; //Image of the movie poster.

    private DirectorResponseDTO director; //Where are we going to convert this, in the MovieServiceImpl

    //What talks to the DB => the repository. MovieRepoInterface.
}
