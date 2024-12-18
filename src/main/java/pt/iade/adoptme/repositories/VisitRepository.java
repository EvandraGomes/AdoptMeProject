package pt.iade.adoptme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iade.adoptme.models.Visit;

import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {
    // Buscar visitas por status
    List<Visit> findByStatus(String status);

    // Buscar visitas de um usuário específico
    List<Visit> findByUserId(int userId);

    // Buscar visitas para um animal específico
    List<Visit> findByAnimalId(int animalId);
}
