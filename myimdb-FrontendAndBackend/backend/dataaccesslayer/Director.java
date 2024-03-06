package com.benzair.myimdb.dataaccesslayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "directors")
@Data
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "directorid")
    private String directorId;

    private String name;

    //Do I need to add a @Column(name = "dob")?
    private LocalDate dob;

    private String country;

    @Column(name = "imageurl")
    private String imageURL;

    //For one director, we'll have many movies.
    @OneToMany(mappedBy = "director")//Means in each movie
    private Set<Movie> movies;

    
}
