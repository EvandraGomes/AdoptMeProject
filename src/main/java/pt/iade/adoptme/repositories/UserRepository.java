package pt.iade.adoptme.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.iade.adoptme.models.User;

import java.util.List;
import java.util.Optional;

/* esse extends "J.... Integer>" é que cria  um repositório
 para o User e configura o tipo de dados do ID dele */

public interface UserRepository extends JpaRepository<User, Integer> {

    // Consulta personalizada para buscar usuário pelo nome
    Iterable<User> findAllByName(String name);

    // Consulta personalizada para buscar usuário pelo e-mail
    Optional<User> findByEmail(String email);

    // Consulta personalizada para buscar usuários pelo telefone
    List<User> findAllByPhone(String phone);

    // Consulta personalizada para buscar usuário pelo ID
    Optional<User> findById(Integer userId);
}


