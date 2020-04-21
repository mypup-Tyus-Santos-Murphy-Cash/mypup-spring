package com.mypup.demo.repos;

import com.mypup.demo.models.DogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DogPostRepo extends JpaRepository<DogPost, Long> {
DogPost findById(long id);
@Query("SELECT dogPost FROM DogPost dogPost WHERE dogPost.id=:dog_post_id")
Optional<DogPost> findDogPostsById(@Param("dog_post_id") Long dog_post_id);
List<DogPost> findByDogBreed(String dogBreed);

}
