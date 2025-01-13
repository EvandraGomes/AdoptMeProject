package pt.iade.adoptme.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.iade.adoptme.models.Visit;

import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer> {

    // Método personalizado para encontrar uma visita agendada (visitDate != null) para um animal específico
    Optional<Visit> findByAnimalIdAndVisitDateIsNotNull(int animalId);
}
