package com.benzair.myimdb.presentationlayer.movies;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieRequestDTO {

    private String title;
    private String directorId;
    private Integer releaseYear;
    private String posterURL;
}
