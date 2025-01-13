package pt.iade.adoptme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.iade.adoptme.models.Visit;
import pt.iade.adoptme.models.User;
import pt.iade.adoptme.models.Animal;
import pt.iade.adoptme.repositories.VisitRepository;
import pt.iade.adoptme.repositories.AnimalRepository;
import pt.iade.adoptme.service.UserService;
import pt.iade.adoptme.service.StatusService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
@RestController
@RequestMapping("/api/visits")
public class VisitController {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private StatusService statusService;

    // Endpoint para agendar uma visita
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleVisit(
            @RequestHeader("Authorization") String token,
            @RequestBody VisitRequest visitRequest) {
        try {
            // Validar e obter o usuário pelo token
            String cleanToken = token.replace("Bearer ", "");
            User user = userService.getUserByToken(cleanToken);

            // Verificar se o animal existe
            Optional<Animal> optionalAnimal = animalRepository.findById(visitRequest.getAnimalId());
            if (optionalAnimal.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Animal não encontrado.");
            }
            Animal animal = optionalAnimal.get();

            // Criar uma nova visita
            Visit visit = new Visit();
            visit.setUser(user);
            visit.setAnimal(animal);
            visit.setVisitDate(visitRequest.getVisitDate());
            visit.setVisitTime(visitRequest.getVisitTime());
            visit.setStatus("visita agendada");

            // Salvar a visita no banco de dados
            visitRepository.save(visit);

            // Atualizar o status do animal para "primeira visita agendada"
            statusService.updateFirstVisitStatus(visitRequest.getAnimalId());

            return ResponseEntity.status(HttpStatus.CREATED).body("Visita agendada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    // Endpoint para obter todas as visitas
    @GetMapping("")
    public ResponseEntity<?> getAllVisits() {
        try {
            // Buscar todas as visitas
            Iterable<Visit> visits = visitRepository.findAll();
            return ResponseEntity.ok(visits);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    // Novo endpoint para verificar o status de uma visita
    @GetMapping("/status/{animalId}")
    public ResponseEntity<?> getVisitStatus(@PathVariable int animalId) {
        try {
            // Usando o método findByAnimalIdAndVisitDateIsNotNull para verificar se a visita está agendada
            Optional<Visit> visit = visitRepository.findByAnimalIdAndVisitDateIsNotNull(animalId);

            if (visit.isPresent()) {
                // Se a visita está agendada, retornamos "true"
                return ResponseEntity.ok(true);
            } else {
                // Caso não haja visita agendada, retornamos "false"
                return ResponseEntity.ok(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        }
    }

    // Classe interna para receber os dados da requisição
    public static class VisitRequest {
        private int animalId;
        private LocalDate visitDate;
        private LocalTime visitTime;

        // Getters e Setters
        public int getAnimalId() {
            return animalId;
        }

        public void setAnimalId(int animalId) {
            this.animalId = animalId;
        }

        public LocalDate getVisitDate() {
            return visitDate;
        }

        public void setVisitDate(LocalDate visitDate) {
            this.visitDate = visitDate;
        }

        public LocalTime getVisitTime() {
            return visitTime;
        }

        public void setVisitTime(LocalTime visitTime) {
            this.visitTime = visitTime;
        }
    }
}
