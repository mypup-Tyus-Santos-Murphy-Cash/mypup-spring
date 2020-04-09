package com.mypup.demo.repos;

import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogPostRepo extends JpaRepository<DogPost, Long> {
DogPost findById(long id);
}
