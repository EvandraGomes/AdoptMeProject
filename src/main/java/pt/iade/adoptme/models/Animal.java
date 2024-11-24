package pt.iade.adoptme.models;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ani_id")
    private int id;

    @Column(name = "ani_name")
    private String name;

    @Column(name = "ani_birthdate")
    private LocalDate birthDate;

    @Column(name = "ani_gender")
    private String gender;

    @Column(name = "ani_description")
    private String description;

    @Column(name = "ani_type")  // Adicionando a coluna para o tipo do animal
    private String type;  // Tipo do animal, como "Cachorro", "Gato", etc.

    // Construtor vazio
    public Animal() {
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {  // Getter para o tipo
        return type;
    }

    // Métodos setter
    public void setDescription(String description) {
        this.description = description;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {  // Setter para o tipo
        this.type = type;
    }
}
