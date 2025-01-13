package pt.iade.adoptme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iade.adoptme.models.Loc;

import java.util.List;

public interface LocRepository extends JpaRepository<Loc, Long> {
    List<Loc> findByLocId(Long locId);
    List<Loc> findByLocType(String locType);
    List<Loc> findByLocLatitudeBetweenAndLocLongitudeBetween(double latMin, double latMax, double lonMin, double lonMax); // Para buscar pontos dentro de uma área
}