package pt.iade.adoptme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.iade.adoptme.models.Favorite;
import pt.iade.adoptme.models.User;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f FROM Favorite f WHERE f.userId = :user")
    List<Favorite> findByUser(@Param("user") User user);
}

