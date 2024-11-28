package pt.iade.adoptme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.adoptme.models.Animal;
import pt.iade.adoptme.models.repositories.AnimalRepository;

import java.net.URL;

@RestController
@RequestMapping(path = "api/animals")
public class AnimalController {
    private final Logger logger = LoggerFactory.getLogger(AnimalController.class);
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Animal> getAnimals(){
        logger.info("Mandando os animais");

        return animalRepository.findAll();
    }

    @GetMapping(path = "/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Animal> getAnimalsByType(@PathVariable String type) {
        logger.info("Filtrar os animais do tipo {}", type);
        if (type.equalsIgnoreCase("todos")) {
            // Retorna todos os animais
            return animalRepository.findAll();
        }
        return animalRepository.findAllByType(type);
    }
    // esse é responsável por pegar a URL da BD e retornar a imagem no Android
    @GetMapping(path = "/{id}/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getAnimalImage(@PathVariable int id) {
        logger.info("Buscando imagem do animal com ID {}", id);


        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado"));

        String imageUrl = animal.getImage();

        try {
            // supostamente faz o download da imagem com a URL
            return new URL(imageUrl).openStream().readAllBytes();
        } catch (Exception e) {
            logger.error("Erro ao carregar imagem do animal: {}", e.getMessage());
            throw new RuntimeException("Não foi possível carregar a imagem do animal");
        }
    }

}
