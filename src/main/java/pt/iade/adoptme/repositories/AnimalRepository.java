package pt.iade.adoptme.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.iade.adoptme.models.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
    // Buscar todos os animais por tipo
    List<Animal> findAllByType(String type);

    // Buscar todos os animais por raça
    List<Animal> findAllByBreed(String breed);

    // Buscar todos os animais por gênero
    List<Animal> findAllByGender(char gender);
}
