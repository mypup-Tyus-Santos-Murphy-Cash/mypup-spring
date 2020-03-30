package com.mypup.demo.repos;

import com.mypup.demo.controllers.BreederPostsController;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository <BreederPostsController, Long> {

    BreederPostsController findById(long id);
    BreederPostsController findByTitle(String title);


}
