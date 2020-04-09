package com.mypup.demo.repos;

import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String users);
    User findByUserRole(String users);
    User findUserById(long id);
}
