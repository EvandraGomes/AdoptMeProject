package pt.iade.adoptme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pt.iade.adoptme.models.Favorite;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query("SELECT f FROM Favorite f WHERE f.userName LIKE %:name% OR f.animalName LIKE %:name%")
    List<Favorite> findByUserNameOrAnimalName(@Param("name") String name);

}
