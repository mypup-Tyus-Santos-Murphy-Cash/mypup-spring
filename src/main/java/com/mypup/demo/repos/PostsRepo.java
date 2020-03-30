package com.mypup.demo.repos;

import com.mypup.demo.controllers.BreederPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository <BreederPosts, Long> {

    BreederPosts findById(long id);


}
