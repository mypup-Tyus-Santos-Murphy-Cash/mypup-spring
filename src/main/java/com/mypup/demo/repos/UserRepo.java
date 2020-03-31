package com.mypup.demo.repos;

import com.mypup.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String users);
//    User findUserById(long id);
}
