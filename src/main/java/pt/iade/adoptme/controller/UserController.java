package pt.iade.adoptme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.repositories.UserRepository;
import pt.iade.adoptme.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint para obter todos os usuários
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Chama o Service
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

    // Endpoint para registrar um novo usuário
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            // Log para verificar o JSON recebido
            logger.info("JSON recebido: " + user.toString());  // Mostra o JSON recebido pelo Spring Boot

            // Chama o serviço que valida e registra o usuário
            userService.registerUser(user);

            // Resposta de sucesso
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
        } catch (Exception e) {
            // Caso ocorra erro, retorna a mensagem de erro
            logger.error("Erro ao registrar usuário: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }


    // Endpoint para login
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        try {
            String token = userService.loginUser(loginDetails.getEmail(), loginDetails.getPassword());
            return ResponseEntity.ok(Map.of("token", token)); // Retorna o token gerado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: " + e.getMessage());
        }
    }

    // Endpoint para obter informações do usuário autenticado
    @GetMapping(path = "/auth/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        try {
            // Extrai o token (removendo o prefixo "Bearer ")
            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserByToken(cleanToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro: " + e.getMessage());
        }
    }




}
