package com.mypup.demo.repos;
import com.mypup.demo.models.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface FavoritesRepo extends JpaRepository<Favorites, Long> {
    @Query("Select favorites  FROM Favorites favorites WHERE favorites.user.id= :user_id and favorites.dogPost=:dog_post_id")
    Optional<Favorites> getFavoritesByUserAndDogPost(@Param("user_id")Long user_id, @Param("dog_post_id")Long dog_post_id);
}