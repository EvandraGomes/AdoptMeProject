package pt.iade.adoptme.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor; // isso aquib faz os getters e setters automaticamente


@Entity
@Data
@NoArgsConstructor
@Table(name = "animal")

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ani_id")
    private int id;

    @Column(name = "ani_name")
    private String name;

    @Column(name = "ani_birthday")
    private LocalDate birthday;

    @Column(name = "ani_gender")
    private String gender;

    @Column(name = "ani_breed")
    private String breed;

    @Column(name = "ani_description")
    private String description;

    @Column(name = "ani_type")
    private String type;  // (cao, gato, passaro, coelho)

    @Column(name = "ani_image")
    private String image;


}