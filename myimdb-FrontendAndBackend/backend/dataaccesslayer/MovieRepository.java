package com.benzair.myimdb.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    //Beginning of code for getting a single movie item.
    Movie findMovieByMovieId(String movieId);
    List<Movie> findMoviesByDirector_DirectorId(String directorId);

    //End of code for getting a single movie item.
}
//Schema defines the tables that are tin the DB, the columns, their values, which is PK SK, null or not null.
//