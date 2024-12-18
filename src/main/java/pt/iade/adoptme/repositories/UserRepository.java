package pt.iade.adoptme.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iade.adoptme.models.User;

/* esse extends "J.... Integer>" é que cria  um repositório
 para o User e configura o tipo de dados do ID dele */

public interface UserRepository extends JpaRepository<User, Integer> {
    // Consulta personalizada para buscar usuário pelo nome
    Iterable<User> findAllByName(String name);
}
