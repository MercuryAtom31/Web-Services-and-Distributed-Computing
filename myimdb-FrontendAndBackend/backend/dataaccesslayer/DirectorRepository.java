package com.benzair.myimdb.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Integer> {

    Director findDirectorByDirectorId(String directorId);
}
