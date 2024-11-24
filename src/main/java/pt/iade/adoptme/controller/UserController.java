package pt.iade.adoptme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.models.repositories.UserRepository;


@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    // Endpoint para obter todos os usuários
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


    // Endpoint para buscar usuários por nome
    @GetMapping(path = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getUsersByName(@PathVariable String name) {
        return userRepository.findAllByName(name);
    }


    // Endpoint para obter um usuário específico pelo id
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}

