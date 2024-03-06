package com.benzair.myimdb.dataaccesslayer;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movies")
@Data//We need to identify the primary key and tell
@NoArgsConstructor//By default, the name of the table is the class name
public class Movie {//We want this class to be an entity (which is what we store in a database).
    //We need to tell Spring Boot that this class is an entity.

    //Database id is another word for primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Each one must be unique.
    private Integer id;//Why is the data type Integer instead of int? To create an object.

    @Column(name = "movieid")
    private String movieId; // public id.

    private String title;

    @Column(name = "releaseyear")
    private Integer releaseYear;

    //private director

    @Column(name = "posterurl")//@Column says, name the column this way.
    private String posterURL; //Image of the movie poster.

    //Reference Column name, creates a foreign key.
    //The name, is the name of the new column in the Movie entity.
    @ManyToOne
    @JoinColumn(name = "directorid", referencedColumnName = "directorid")
    private Director director;



    //What talks to the DB => the repository. MovieRepoInterface.

}
