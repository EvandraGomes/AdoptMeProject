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
public Iterable<Animal> getAnimalstype(@PathVariable String type) {
    logger.info("Filtrando os animais do tipo {}", type);
    return animalRepository.findAllByType(type);
}

}

