package com.mypup.demo.repos;

import com.mypup.demo.models.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreedRepo extends JpaRepository<Breed, Long> {

}
