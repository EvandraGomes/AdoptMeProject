package pt.iade.adoptme.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fav_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "fav_usr_id", referencedColumnName = "usr_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "fav_ani_id", referencedColumnName = "ani_id")
    private Animal animalId;

    @Column(name = "fav_date")
    private LocalDateTime date;

    @Column(name = "fav_usr_name")
    private String userName;

    @Column(name = "fav_ani_name")
    private String animalName;

    @Column(name = "fav_ani_image")
    private String animalImage;


}