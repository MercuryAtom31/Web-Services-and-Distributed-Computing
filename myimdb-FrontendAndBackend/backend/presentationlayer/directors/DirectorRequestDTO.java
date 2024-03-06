package com.benzair.myimdb.presentationlayer.directors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DirectorRequestDTO {

    private String name;

    private LocalDate dob;

    private String country;

    private String imageURL;
}
