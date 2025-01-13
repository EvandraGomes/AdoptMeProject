package pt.iade.adoptme.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iade.adoptme.models.Status;
import pt.iade.adoptme.repositories.StatusRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.iade.adoptme.models.Status;
import pt.iade.adoptme.repositories.StatusRepository;

import java.util.Optional;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public void updateFirstVisitStatus(int animalId) {
        // Tenta buscar o status do animal pelo ID
        Optional<Status> optionalStatus = statusRepository.findByAnimalId(animalId);
        Status status;

        if (optionalStatus.isPresent()) {
            // Se o status já existe, recupera-o
            status = optionalStatus.get();
        } else {
            // Caso contrário, cria um novo status para o animal
            status = new Status();
            status.setAnimalId(animalId);
        }

        // Atualiza o status para "primeira visita agendada"
        status.setFirstVisit(true);

        // Salva o status atualizado no banco de dados
        statusRepository.save(status);
    }
}
