package com.benzair.myimdb.presentationlayer.directors;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DirectorResponseDTO {

    private String directorId;

    private String name;

    private LocalDate dob;

    private String country;

    private String imageURL;
}
