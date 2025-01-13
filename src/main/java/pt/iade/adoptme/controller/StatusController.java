package pt.iade.adoptme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iade.adoptme.models.Animal;
import pt.iade.adoptme.models.Status;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.repositories.AnimalRepository;
import pt.iade.adoptme.repositories.StatusRepository;
import pt.iade.adoptme.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserService userService;

    // Endpoint para atualizar o status para 'visita agendada'
    @PostMapping("/update")
    public ResponseEntity<?> updateStatus(
            @RequestHeader("Authorization") String token,
            @RequestBody StatusUpdateRequest statusUpdateRequest) {
        try {
            // Validar e obter o usuário pelo token
            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserByToken(cleanToken);

            // Verificar se o animal existe
            Optional<Animal> optionalAnimal = animalRepository.findById(statusUpdateRequest.getAnimalId());
            if (optionalAnimal.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
            }
            Animal animal = optionalAnimal.get();

            Optional<Status> optionalStatus = statusRepository.findByAnimalId(animal.getId());
            Status status;
            if (optionalStatus.isPresent()) {
                status = optionalStatus.get();
            } else {
                // Criar um novo status se não existir
                status = new Status();
                status.setAnimalId(animal.getId()); // Define o ID do animal
            }

            status.setFirstVisit(true); // Marca como visita agendada
            statusRepository.save(status); // Salva o status atualizado

            return ResponseEntity.status(HttpStatus.OK).body("Status atualizado para 'visita agendada'.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    // Endpoint para atualizar o status utilizando PUT
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(
            @RequestHeader("Authorization") String token,
            @PathVariable int id, // Recebe o id do status na URL
            @RequestBody StatusUpdateRequest statusUpdateRequest) {
        try {
            // Validar e obter o usuário pelo token
            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserByToken(cleanToken);

            // Verificar se o status existe
            Optional<Status> optionalStatus = statusRepository.findById(id);
            if (optionalStatus.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status não encontrado.");
            }
            Status status = optionalStatus.get();

            // Verificar se o animal existe
            Optional<Animal> optionalAnimal = animalRepository.findById(statusUpdateRequest.getAnimalId());
            if (optionalAnimal.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
            }
            Animal animal = optionalAnimal.get();

            // Atualizar o status
            status.setAnimalId(animal.getId());
            status.setFirstVisit(true); // Marca como visita agendada

            statusRepository.save(status); // Salva o status atualizado

            return ResponseEntity.status(HttpStatus.OK).body("Status atualizado para 'visita agendada'.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    // Classe interna para o corpo da requisição StatusUpdateRequest
    public static class StatusUpdateRequest {
        private int animalId;

        // Getter e Setter para o animalId
        public int getAnimalId() {
            return animalId;
        }

        public void setAnimalId(int animalId) {
            this.animalId = animalId;
        }
    }
}
