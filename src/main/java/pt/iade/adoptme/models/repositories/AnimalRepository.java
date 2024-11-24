package pt.iade.adoptme.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.iade.adoptme.models.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    // Método automático baseado em query derivada
    List<Animal> findAllByType(String type);
   
}


