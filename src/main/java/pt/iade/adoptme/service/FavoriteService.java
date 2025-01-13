package pt.iade.adoptme.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iade.adoptme.models.Animal;
import pt.iade.adoptme.models.Favorite;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.repositories.FavoriteRepository;
import pt.iade.adoptme.repositories.AnimalRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteService {

    private static final Logger logger = LoggerFactory.getLogger(FavoriteService.class);

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private AnimalRepository animalRepository;  // Repositório do Animal para buscar pelo id

    // Método para adicionar um favorito
    public Favorite addFavorite(Favorite favorite, User user) {
        // Recupera o Animal completo usando o id
        Animal animal = animalRepository.findById(favorite.getAnimalId().getId()).orElse(null);

        if (animal == null) {
            throw new RuntimeException("Animal não encontrado");
        }

        // Define a data do favorito
        favorite.setDate(LocalDateTime.now());

        // Associa o usuário ao favorito
        favorite.setUserId(user);

        // Associa os dados do animal ao favorito
        favorite.setAnimalId(animal);  // O objeto animal completo
        favorite.setUserName(user.getName());  // Nome do usuário
        favorite.setAnimalName(animal.getName());  // Nome do animal
        favorite.setAnimalImage(animal.getImage());  // Imagem do animal

        // Salva o favorito no repositório e retorna
        return favoriteRepository.save(favorite);
    }

    // Método para pegar os favoritos de um usuário
    public List<Favorite> getFavoritesByUser(User user) {
        logger.info("Pegar favoritos para o usuário: {}", user.getEmail());
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        logger.info("Quantidade de favoritos encontrados: {}", favorites.size());
        return favorites;
    }
}
