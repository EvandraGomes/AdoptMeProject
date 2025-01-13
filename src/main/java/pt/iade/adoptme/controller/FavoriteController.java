package pt.iade.adoptme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iade.adoptme.models.Animal;
import pt.iade.adoptme.models.Favorite;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.service.FavoriteService;
import pt.iade.adoptme.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/favorites")
public class FavoriteController {

    private final Logger logger = LoggerFactory.getLogger(FavoriteController.class);

    @Autowired
    private FavoriteService favoriteService;  // Usando o serviço de favoritos

    @Autowired
    private UserService userService;  // Para recuperar o usuário autenticado pelo token

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addFavorite(@RequestHeader("Authorization") String token, @RequestBody Favorite favorite) {
        try {
            String cleanToken = token.replace("Bearer ", "");

            // Recupera o usuário autenticado com o token
            User user = userService.getUserByToken(cleanToken);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
            }

            // Adiciona o favorito usando o serviço
            favoriteService.addFavorite(favorite, user);

            logger.info("Favorito adicionado para o usuário: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body("Favorito adicionado com sucesso.");
        } catch (Exception e) {
            logger.error("Erro ao adicionar favorito: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar favorito: " + e.getMessage());
        }
    }

    @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Animal>> getFavorites(@RequestHeader("Authorization") String token) {
        try {
            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserByToken(cleanToken);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            // Recupera os favoritos para o usuário
            List<Favorite> favorites = favoriteService.getFavoritesByUser(user);

            // Mapeia a lista de favoritos para a lista de animais
            List<Animal> animals = favorites.stream()
                    .map(fav -> {
                        Animal animal = fav.getAnimalId();  // Aqui já pegamos o Animal completo
                        animal.setId(fav.getAnimalId().getId());  // Ajusta o ID se necessário
                        return animal;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
