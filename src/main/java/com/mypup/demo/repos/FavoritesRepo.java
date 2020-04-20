package com.mypup.demo.repos;
import com.mypup.demo.models.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepo extends JpaRepository<Favorites, Long> {
}
