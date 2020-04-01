package com.mypup.demo.repos;

import com.mypup.demo.models.DogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogPostRepo extends JpaRepository<DogPost, Long> {
DogPost findById(long id);

}
