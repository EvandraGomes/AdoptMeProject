package pt.iade.adoptme.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sta_id")
    private int id;

    @Column(name = "sta_ani_id")
    private int animalId;

    @Column(name = "sta_first_visit")
    private boolean firstVisit;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAnimalId() { return animalId; }
    public void setAnimalId(int animalId) { this.animalId = animalId; }

    public boolean isFirstVisit() { return firstVisit; }
    public void setFirstVisit(boolean firstVisit) { this.firstVisit = firstVisit; }
}
