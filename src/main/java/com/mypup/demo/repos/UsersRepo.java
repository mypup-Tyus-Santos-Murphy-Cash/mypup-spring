package com.mypup.demo.repos;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Long> {
    User findByUsername(String users);
    User findUserById(long id);
}
